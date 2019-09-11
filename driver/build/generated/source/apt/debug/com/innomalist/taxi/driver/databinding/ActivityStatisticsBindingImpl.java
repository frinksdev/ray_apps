package com.innomalist.taxi.driver.databinding;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityStatisticsBindingImpl extends ActivityStatisticsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar_layout, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.tab_date, 5);
        sViewsWithIds.put(R.id.income_card, 6);
        sViewsWithIds.put(R.id.income_text, 7);
        sViewsWithIds.put(R.id.income_label, 8);
        sViewsWithIds.put(R.id.service_card, 9);
        sViewsWithIds.put(R.id.service_text, 10);
        sViewsWithIds.put(R.id.service_label, 11);
        sViewsWithIds.put(R.id.rating_card, 12);
        sViewsWithIds.put(R.id.rating_text, 13);
        sViewsWithIds.put(R.id.rating_label, 14);
        sViewsWithIds.put(R.id.chart_card, 15);
        sViewsWithIds.put(R.id.chart, 16);
        sViewsWithIds.put(R.id.payment_request_card, 17);
        sViewsWithIds.put(R.id.label_payment_request, 18);
        sViewsWithIds.put(R.id.text_payment_bound, 19);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityStatisticsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private ActivityStatisticsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.appbar.AppBarLayout) bindings[3]
            , (android.widget.Button) bindings[2]
            , (com.db.chart.view.LineChartView) bindings[16]
            , (androidx.cardview.widget.CardView) bindings[15]
            , (androidx.cardview.widget.CardView) bindings[6]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[18]
            , (androidx.cardview.widget.CardView) bindings[17]
            , (android.widget.ProgressBar) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[12]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[13]
            , (androidx.cardview.widget.CardView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (com.google.android.material.tabs.TabLayout) bindings[5]
            , (android.widget.TextView) bindings[19]
            , (androidx.appcompat.widget.Toolbar) bindings[4]
            );
        this.buttonPaymentRequest.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressPayment.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.driver == variableId) {
            setDriver((com.innomalist.taxi.common.models.Driver) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDriver(@Nullable com.innomalist.taxi.common.models.Driver Driver) {
        updateRegistration(0, Driver);
        this.mDriver = Driver;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.driver);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeDriver((com.innomalist.taxi.common.models.Driver) object, fieldId);
        }
        return false;
    }
    private boolean onChangeDriver(com.innomalist.taxi.common.models.Driver Driver, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        java.lang.Double driverBalance = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50 = false;
        int intAndroidxDatabindingViewDataBindingSafeUnboxDriverBalance = 0;
        double androidxDatabindingViewDataBindingSafeUnboxDriverBalance = 0.0;
        boolean androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50BooleanTrueBooleanFalse = false;
        com.innomalist.taxi.common.models.Driver driver = mDriver;

        if ((dirtyFlags & 0x3L) != 0) {



                if (driver != null) {
                    // read driver.balance
                    driverBalance = driver.getBalance();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(driver.balance)
                androidxDatabindingViewDataBindingSafeUnboxDriverBalance = androidx.databinding.ViewDataBinding.safeUnbox(driverBalance);


                // read androidx.databinding.ViewDataBinding.safeUnbox(driver.balance) >= 50
                androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50 = (androidxDatabindingViewDataBindingSafeUnboxDriverBalance) >= (50);
                // read (int) androidx.databinding.ViewDataBinding.safeUnbox(driver.balance)
                intAndroidxDatabindingViewDataBindingSafeUnboxDriverBalance = ((int) (androidxDatabindingViewDataBindingSafeUnboxDriverBalance));
            if((dirtyFlags & 0x3L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(driver.balance) >= 50 ? true : false
                androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50BooleanTrueBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50) ? (true) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.buttonPaymentRequest.setEnabled(androidxDatabindingViewDataBindingSafeUnboxDriverBalanceInt50BooleanTrueBooleanFalse);
            this.progressPayment.setProgress(intAndroidxDatabindingViewDataBindingSafeUnboxDriverBalance);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.progressPayment.setMax(progressPayment.getResources().getInteger(R.integer.minimum_payment_request));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): driver
        flag 1 (0x2L): null
        flag 2 (0x3L): androidx.databinding.ViewDataBinding.safeUnbox(driver.balance) >= 50 ? true : false
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(driver.balance) >= 50 ? true : false
    flag mapping end*/
    //end
}