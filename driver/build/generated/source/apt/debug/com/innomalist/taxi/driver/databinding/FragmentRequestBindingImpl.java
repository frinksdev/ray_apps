package com.innomalist.taxi.driver.databinding;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentRequestBindingImpl extends FragmentRequestBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layout_top, 4);
        sViewsWithIds.put(R.id.icon_car, 5);
        sViewsWithIds.put(R.id.guideline_start, 6);
        sViewsWithIds.put(R.id.icon_user, 7);
        sViewsWithIds.put(R.id.line_driver_user, 8);
        sViewsWithIds.put(R.id.text_driver_user_distance, 9);
        sViewsWithIds.put(R.id.line_user_destination, 10);
        sViewsWithIds.put(R.id.text_user_destination_distance, 11);
        sViewsWithIds.put(R.id.icon_destination, 12);
        sViewsWithIds.put(R.id.icon_pickup, 13);
        sViewsWithIds.put(R.id.line_pickup_dropoff, 14);
        sViewsWithIds.put(R.id.icon_dropoff, 15);
        sViewsWithIds.put(R.id.label_from, 16);
        sViewsWithIds.put(R.id.label_to, 17);
        sViewsWithIds.put(R.id.icon_cost, 18);
        sViewsWithIds.put(R.id.label_cost, 19);
        sViewsWithIds.put(R.id.divider, 20);
        sViewsWithIds.put(R.id.button_accept, 21);
        sViewsWithIds.put(R.id.button_decline, 22);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentRequestBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private FragmentRequestBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[21]
            , (android.widget.Button) bindings[22]
            , (android.widget.ImageView) bindings[20]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[5]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[18]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[12]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[15]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[13]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[7]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[11]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textCost.setTag(null);
        this.textFrom.setTag(null);
        this.textTo.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.distanceDriver == variableId) {
            setDistanceDriver((java.lang.Integer) variable);
        }
        else if (BR.travel == variableId) {
            setTravel((com.innomalist.taxi.common.models.Travel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDistanceDriver(@Nullable java.lang.Integer DistanceDriver) {
        this.mDistanceDriver = DistanceDriver;
    }
    public void setTravel(@Nullable com.innomalist.taxi.common.models.Travel Travel) {
        updateRegistration(0, Travel);
        this.mTravel = Travel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.travel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeTravel((com.innomalist.taxi.common.models.Travel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeTravel(com.innomalist.taxi.common.models.Travel Travel, int fieldId) {
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
        java.lang.Double travelCostBest = null;
        java.lang.String travelDestinationAddress = null;
        java.lang.String textCostAndroidStringUnitMoneyTravelCostBest = null;
        java.lang.String travelPickupAddress = null;
        com.innomalist.taxi.common.models.Travel travel = mTravel;

        if ((dirtyFlags & 0x5L) != 0) {



                if (travel != null) {
                    // read travel.costBest
                    travelCostBest = travel.getCostBest();
                    // read travel.destinationAddress
                    travelDestinationAddress = travel.getDestinationAddress();
                    // read travel.pickupAddress
                    travelPickupAddress = travel.getPickupAddress();
                }


                // read @android:string/unit_money
                textCostAndroidStringUnitMoneyTravelCostBest = textCost.getResources().getString(R.string.unit_money, travelCostBest);
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textCost, textCostAndroidStringUnitMoneyTravelCostBest);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textFrom, travelPickupAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textTo, travelDestinationAddress);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): travel
        flag 1 (0x2L): distanceDriver
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}