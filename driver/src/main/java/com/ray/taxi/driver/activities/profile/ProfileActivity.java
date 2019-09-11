package com.ray.taxi.driver.activities.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.innomalist.taxi.common.components.BaseActivity;
import com.innomalist.taxi.common.events.ChangeProfileImageResultEvent;
import com.innomalist.taxi.common.events.EditProfileInfoResultEvent;
import com.innomalist.taxi.common.models.Driver;
import com.innomalist.taxi.common.models.Media;
import com.innomalist.taxi.common.models.Service;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.AlerterHelper;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.MyPreferenceManager;
import com.innomalist.taxi.common.utils.Validators;
import com.innomalist.taxi.driver.R;
import com.ray.taxi.driver.activities.profile.adapters.DocumentsRecyclerViewAdapter;
import com.ray.taxi.driver.activities.profile.adapters.ServicesRecyclerViewAdapter;
import com.innomalist.taxi.driver.databinding.ActivityEditProfileBinding;
import com.ray.taxi.driver.events.ChangeHeaderImageResultEvent;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends BaseActivity {
    private static final int PICK_DOCUMENT = 74;
    ActivityEditProfileBinding binding;
    Driver driver = new Driver();
    ImageToUpload imageToUpload = ImageToUpload.profile;
    MyPreferenceManager SP;
    enum ImageToUpload {
        profile,
        header,
        document
    }
    List<Integer> enabledServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SP = MyPreferenceManager.getInstance(getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.genders));
        binding.spinnerGender.setAdapter(adapter);
        new RequestInfo().execute(String.valueOf(CommonUtils.driver.getMobileNumber()));
        binding.profileImage.setOnClickListener(onProfileImageClicked);
        initializeToolbar("");
        this.toolbar.setHomeAsUpIndicator(R.drawable.ic_close);
        if(CommonUtils.driver.getStatus() == Driver.Status.SOFT_REJECT)
            binding.textNote.setText(getString(R.string.soft_reject_notice, CommonUtils.driver.getDocumentsNote()));
        else
            binding.layoutNote.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (driver.getEmail() != null && !driver.getEmail().equals("") && !Validators.validateEmailAddress(driver.getEmail())) {
            AlerterHelper.showError(ProfileActivity.this, getString(R.string.error_invalid_email));
            return false;
        }
        new RegisterDriver().execute(binding.getUser().toJson(), enabledServices.toString());
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener onProfileImageClicked = new View.OnClickListener() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                ImagePicker.create(ProfileActivity.this)
                        .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                        .folderMode(true) // folder mode (false by default)
                        .toolbarFolderTitle(getString(R.string.picker_folder)) // folder selection title
                        .toolbarImageTitle(getString(R.string.picker_tap_select)) // image selection title
                        .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                        .single() // single mode
                        .theme(R.style.ImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                        .start();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        @Override
        public void onClick(View v) {
            imageToUpload = ImageToUpload.profile;
            TedPermission.with(ProfileActivity.this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage(getString(R.string.message_permission_denied))
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .check();

        }
    };

    void saveDriverInfo() {
        SP.putString("driver_user", new Gson().toJson(CommonUtils.driver));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfileInfoChanged(EditProfileInfoResultEvent event) {
        if (event.hasError()) {
            event.showAlert(ProfileActivity.this);
            return;
        }
        setResult(RESULT_OK);
        finish();
        CommonUtils.driver = driver;
        saveDriverInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfileImageChanged(ChangeProfileImageResultEvent event) {
        binding.getUser().setMedia(event.media);
        saveDriverInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHeaderImageChanged(ChangeHeaderImageResultEvent event) {
        binding.getUser().setCarMedia(event.media);
        saveDriverInfo();
    }

    public void onProfileImageClicked(View view) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CommonUtils.MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    switch (imageToUpload) {
                        case profile:
                            onProfileImageClicked(binding.profileImage);
                            break;

                        case header:
                            onHeaderImageClicked(binding.cameraHeaderImage);
                            break;

                        case document:
                            onUploadDocument(null);
                            break;
                    }
                } else {
                    AlertDialogBuilder.show(ProfileActivity.this, getString(R.string.prompt_storage_rw));
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "p.jpg"));
            UCrop.of(Uri.fromFile(new File(image.getPath())), destinationUri)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(200, 200)
                    .start(ProfileActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri == null)
                return;
            switch (imageToUpload) {
                case profile:
                    new UploadDocument().execute("driver image", resultUri.getPath());
                    break;

                case header:
                    new UploadDocument().execute("driver header", resultUri.getPath());

                    break;

                case document:
                    new UploadDocument().execute("document", resultUri.getPath());
                    break;
            }
        } else if (resultCode == UCrop.RESULT_ERROR)
            try {
                throw UCrop.getError(data);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        if (requestCode == PICK_DOCUMENT)
            if (resultCode == Activity.RESULT_OK) {

            }
    }


    public void onHeaderImageClicked(View view) {
        imageToUpload = ImageToUpload.header;
        TedPermission.with(ProfileActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.message_permission_denied))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            ImagePicker.create(ProfileActivity.this)
                    .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                    .folderMode(true) // folder mode (false by default)
                    .toolbarFolderTitle(getString(R.string.picker_folder)) // folder selection title
                    .toolbarImageTitle(getString(R.string.picker_tap_select)) // image selection title
                    .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                    .single() // single mode
                    .theme(R.style.ImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                    .start();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }
    };

    public void onUploadDocument(View view) {
        imageToUpload = ImageToUpload.document;
        TedPermission.with(ProfileActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.message_permission_denied))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private class RequestInfo extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL(getString(R.string.server_address) + "driver_get_registration");
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(client.getOutputStream());

                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("number", uri[0]);
                StringBuilder result = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : postDataParams.entrySet()) {
                    if (first)
                        first = false;
                    else
                        result.append("&");
                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                wr.write(result.toString().getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    sb.append(line);
                return sb.toString();
            } catch (Exception c) {
                c.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                Driver _driver = Driver.fromJson(result);
                binding.setUser(_driver);
                List<Media> documents = _driver.getDocuments();
                if(documents == null) {
                    documents = new ArrayList<>();
                }
                DocumentsRecyclerViewAdapter documentsRecyclerViewAdapter = new DocumentsRecyclerViewAdapter(documents, media -> AlertDialogBuilder.show(ProfileActivity.this, "Item Clicked"));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProfileActivity.this, RecyclerView.HORIZONTAL, false);
                binding.documents.setLayoutManager(linearLayoutManager);
                binding.documents.setAdapter(documentsRecyclerViewAdapter);
                List<Service> services = _driver.getServices();
                if(services == null) {
                    services = new ArrayList<>();
                }
                ServicesRecyclerViewAdapter servicesRecyclerViewAdapter = new ServicesRecyclerViewAdapter(services, new ServicesRecyclerViewAdapter.OnServiceItemInteractionListener() {
                    @Override
                    public void onChecked(Service service) {
                        if(!enabledServices.contains(service.getId()))
                            enabledServices.add(service.getId());
                    }

                    @Override
                    public void onUnchecked(Service service) {
                        if(enabledServices.contains(service.getId()))
                            enabledServices.remove(service.getId());
                    }
                });
                LinearLayoutManager servicesLayoutManager = new LinearLayoutManager(ProfileActivity.this, RecyclerView.VERTICAL, false);
                binding.services.setLayoutManager(servicesLayoutManager);
                binding.services.setAdapter(servicesRecyclerViewAdapter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class RegisterDriver extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL(getString(R.string.server_address) + "driver_register");
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(client.getOutputStream());

                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("driver", uri[0]);
                postDataParams.put("services", uri[1]);
                StringBuilder result = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : postDataParams.entrySet()) {
                    if (first)
                        first = false;
                    else
                        result.append("&");
                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                wr.write(result.toString().getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    sb.append(line);
                return sb.toString();
            } catch (Exception c) {
                c.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("200")) {
                AlertDialogBuilder.show(ProfileActivity.this, "Your info is sent successfully to admin for approval. As soon as your documentation was approved you will be able to receive travels and start working.", AlertDialogBuilder.DialogButton.OK, result1 -> finish());
                finish();
            } else {
                AlertDialogBuilder.show(ProfileActivity.this, String.format("Something went wrong. Here is the error: %s",result));
            }
        }
    }

    private class UploadDocument extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1024 * 1024;
                File sourceFile = new File(params[1]);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = getString(R.string.server_address) + "upload";
                        String fileName = "doc.jpg";
                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                        //conn.setRequestProperty("file", params[0]);
                        conn.setRequestProperty("number", String.valueOf(CommonUtils.driver.getMobileNumber()));
                        conn.setRequestProperty("type",params[0]);
                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
                                + fileName + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();
                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            //Toast.makeText(ctx, "File Upload Complete.",
                            //      Toast.LENGTH_SHORT).show();

                            // recursiveDelete(mDirectory1);
                            return "OK";
                        } else {
                            return "FAILED";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "FAILED";
                    }
                } else {
                    return "FAILED";
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                return "FAILED";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            AlertDialogBuilder.show(ProfileActivity.this, result);
            new RequestInfo().execute(String.valueOf(CommonUtils.driver.getMobileNumber()));
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
