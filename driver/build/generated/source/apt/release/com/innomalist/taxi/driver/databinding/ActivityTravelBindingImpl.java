package com.innomalist.taxi.driver.databinding;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTravelBindingImpl extends ActivityTravelBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layout_actions, 1);
        sViewsWithIds.put(R.id.in_location_button, 2);
        sViewsWithIds.put(R.id.call_button, 3);
        sViewsWithIds.put(R.id.chat_button, 4);
        sViewsWithIds.put(R.id.distance_label, 5);
        sViewsWithIds.put(R.id.time_label, 6);
        sViewsWithIds.put(R.id.distance_text, 7);
        sViewsWithIds.put(R.id.eta_text, 8);
        sViewsWithIds.put(R.id.cost_label, 9);
        sViewsWithIds.put(R.id.cost_text, 10);
        sViewsWithIds.put(R.id.layout_buttons, 11);
        sViewsWithIds.put(R.id.slide_start, 12);
        sViewsWithIds.put(R.id.slide_finish, 13);
        sViewsWithIds.put(R.id.slide_cancel, 14);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTravelBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityTravelBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[3]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[4]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[2]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[11]
            , (ng.max.slideview.SlideView) bindings[14]
            , (ng.max.slideview.SlideView) bindings[13]
            , (ng.max.slideview.SlideView) bindings[12]
            , (android.widget.TextView) bindings[6]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}