package com.innomalist.taxi.driver.databinding;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityEditProfileBindingImpl extends ActivityEditProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar_layout, 13);
        sViewsWithIds.put(R.id.collapse_toolbar, 14);
        sViewsWithIds.put(R.id.camera_header_image, 15);
        sViewsWithIds.put(R.id.upload_ride_label, 16);
        sViewsWithIds.put(R.id.toolbar, 17);
        sViewsWithIds.put(R.id.fields_layout, 18);
        sViewsWithIds.put(R.id.label_status, 19);
        sViewsWithIds.put(R.id.layout_note, 20);
        sViewsWithIds.put(R.id.label_note, 21);
        sViewsWithIds.put(R.id.mobile_text_layout, 22);
        sViewsWithIds.put(R.id.email_text_layout, 23);
        sViewsWithIds.put(R.id.first_name_text_layout, 24);
        sViewsWithIds.put(R.id.last_name_text_layout, 25);
        sViewsWithIds.put(R.id.car_color_text_layout, 26);
        sViewsWithIds.put(R.id.plate_num_text_layout, 27);
        sViewsWithIds.put(R.id.address_text_layout, 28);
        sViewsWithIds.put(R.id.services, 29);
        sViewsWithIds.put(R.id.documents, 30);
        sViewsWithIds.put(R.id.button_upload, 31);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.EditText mboundView10;
    @NonNull
    private final android.widget.EditText mboundView11;
    @NonNull
    private final android.widget.EditText mboundView12;
    @NonNull
    private final androidx.appcompat.widget.AppCompatEditText mboundView5;
    @NonNull
    private final androidx.appcompat.widget.AppCompatEditText mboundView6;
    @NonNull
    private final android.widget.EditText mboundView7;
    @NonNull
    private final android.widget.EditText mboundView8;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView10androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.carColor
            //         is user.setCarColor((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView10);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;
            // user.carColor
            java.lang.String userCarColor = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setCarColor(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView11androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.carPlate
            //         is user.setCarPlate((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView11);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;
            // user.carPlate
            java.lang.String userCarPlate = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setCarPlate(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView12androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.address
            //         is user.setAddress((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView12);
            // localize variables for thread safety
            // user.address
            java.lang.String userAddress = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setAddress(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView6androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.email
            //         is user.setEmail((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView6);
            // localize variables for thread safety
            // user.email
            java.lang.String userEmail = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setEmail(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView7androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.firstName
            //         is user.setFirstName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView7);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user.firstName
            java.lang.String userFirstName = null;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setFirstName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView8androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.lastName
            //         is user.setLastName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView8);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;
            // user.lastName
            java.lang.String userLastName = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setLastName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener spinnerGendergenderAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.gender
            //         is user.setGender((com.innomalist.taxi.common.models.Gender) callbackArg_0)
            com.innomalist.taxi.common.models.Gender callbackArg_0 = com.innomalist.taxi.common.utils.DataBinder.getGender(spinnerGender);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.innomalist.taxi.common.models.Driver user = mUser;
            // user.gender
            com.innomalist.taxi.common.models.Gender userGender = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setGender(((com.innomalist.taxi.common.models.Gender) (callbackArg_0)));
            }
        }
    };

    public ActivityEditProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds));
    }
    private ActivityEditProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.textfield.TextInputLayout) bindings[28]
            , (com.google.android.material.appbar.AppBarLayout) bindings[13]
            , (androidx.appcompat.widget.AppCompatButton) bindings[31]
            , (android.widget.ImageView) bindings[15]
            , (com.google.android.material.textfield.TextInputLayout) bindings[26]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[14]
            , (androidx.recyclerview.widget.RecyclerView) bindings[30]
            , (com.google.android.material.textfield.TextInputLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[18]
            , (com.google.android.material.textfield.TextInputLayout) bindings[24]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[19]
            , (com.google.android.material.textfield.TextInputLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.ImageView) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[22]
            , (com.google.android.material.textfield.TextInputLayout) bindings[27]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[29]
            , (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) bindings[9]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (androidx.appcompat.widget.Toolbar) bindings[17]
            , (android.widget.TextView) bindings[16]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.EditText) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.EditText) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (android.widget.EditText) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView5 = (androidx.appcompat.widget.AppCompatEditText) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (androidx.appcompat.widget.AppCompatEditText) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.EditText) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.EditText) bindings[8];
        this.mboundView8.setTag(null);
        this.media.setTag(null);
        this.profileImage.setTag(null);
        this.spinnerGender.setTag(null);
        this.textNote.setTag(null);
        this.textStatus.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.user == variableId) {
            setUser((com.innomalist.taxi.common.models.Driver) variable);
        }
        else if (BR.converter == variableId) {
            setConverter((com.innomalist.taxi.common.utils.Converters) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable com.innomalist.taxi.common.models.Driver User) {
        updateRegistration(0, User);
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    public void setConverter(@Nullable com.innomalist.taxi.common.utils.Converters Converter) {
        this.mConverter = Converter;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUser((com.innomalist.taxi.common.models.Driver) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUser(com.innomalist.taxi.common.models.Driver User, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.carMedia) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.media) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.gender) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String stringValueOfUserMobileNumber = null;
        com.innomalist.taxi.common.models.Driver user = mUser;
        com.innomalist.taxi.common.models.Media userCarMedia = null;
        com.innomalist.taxi.common.models.Gender userGender = null;
        java.lang.String userDocumentsNote = null;
        java.lang.String userStatusValue = null;
        java.lang.String userEmail = null;
        com.innomalist.taxi.common.models.Media userMedia = null;
        java.lang.String userAddress = null;
        java.lang.String userFirstName = null;
        java.lang.String userCarColor = null;
        java.lang.String userLastName = null;
        java.lang.String userCarPlate = null;
        long userMobileNumber = 0;
        com.innomalist.taxi.common.models.Driver.Status userStatus = null;

        if ((dirtyFlags & 0x3dL) != 0) {


            if ((dirtyFlags & 0x25L) != 0) {

                    if (user != null) {
                        // read user.carMedia
                        userCarMedia = user.getCarMedia();
                    }
            }
            if ((dirtyFlags & 0x31L) != 0) {

                    if (user != null) {
                        // read user.gender
                        userGender = user.getGender();
                    }
            }
            if ((dirtyFlags & 0x21L) != 0) {

                    if (user != null) {
                        // read user.documentsNote
                        userDocumentsNote = user.getDocumentsNote();
                        // read user.email
                        userEmail = user.getEmail();
                        // read user.address
                        userAddress = user.getAddress();
                        // read user.firstName
                        userFirstName = user.getFirstName();
                        // read user.carColor
                        userCarColor = user.getCarColor();
                        // read user.lastName
                        userLastName = user.getLastName();
                        // read user.carPlate
                        userCarPlate = user.getCarPlate();
                        // read user.mobileNumber
                        userMobileNumber = user.getMobileNumber();
                        // read user.status
                        userStatus = user.getStatus();
                    }


                    // read String.valueOf(user.mobileNumber)
                    stringValueOfUserMobileNumber = java.lang.String.valueOf(userMobileNumber);
                    if (userStatus != null) {
                        // read user.status.value
                        userStatusValue = userStatus.getValue();
                    }
            }
            if ((dirtyFlags & 0x29L) != 0) {

                    if (user != null) {
                        // read user.media
                        userMedia = user.getMedia();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x21L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, userCarColor);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView11, userCarPlate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView12, userAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, stringValueOfUserMobileNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, userEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, userFirstName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, userLastName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textNote, userDocumentsNote);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textStatus, userStatusValue);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView10, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView10androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView11, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView11androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView12, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView12androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView6, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView6androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView7, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView7androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView8, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView8androidTextAttrChanged);
            com.innomalist.taxi.common.utils.DataBinder.bindGenderChanged(this.spinnerGender, spinnerGendergenderAttrChanged);
        }
        if ((dirtyFlags & 0x25L) != 0) {
            // api target 1

            com.innomalist.taxi.common.utils.DataBinder.setMedia(this.media, userCarMedia);
        }
        if ((dirtyFlags & 0x29L) != 0) {
            // api target 1

            com.innomalist.taxi.common.utils.DataBinder.setMedia(this.profileImage, userMedia);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            com.innomalist.taxi.common.utils.DataBinder.setGender(this.spinnerGender, userGender);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): converter
        flag 2 (0x3L): user.carMedia
        flag 3 (0x4L): user.media
        flag 4 (0x5L): user.gender
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}