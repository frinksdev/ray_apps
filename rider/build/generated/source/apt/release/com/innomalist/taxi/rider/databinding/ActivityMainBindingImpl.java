package com.innomalist.taxi.rider.databinding;
import com.innomalist.taxi.rider.R;
import com.innomalist.taxi.rider.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.map_layout, 1);
        sViewsWithIds.put(R.id.search_place, 2);
        sViewsWithIds.put(R.id.search_text, 3);
        sViewsWithIds.put(R.id.image_pickup, 4);
        sViewsWithIds.put(R.id.image_destination, 5);
        sViewsWithIds.put(R.id.button_confirm_pickup, 6);
        sViewsWithIds.put(R.id.button_confirm_destination, 7);
        sViewsWithIds.put(R.id.bottom_sheet, 8);
        sViewsWithIds.put(R.id.button_time, 9);
        sViewsWithIds.put(R.id.button_request, 10);
        sViewsWithIds.put(R.id.service_types_view_pager, 11);
        sViewsWithIds.put(R.id.tab_categories, 12);
        sViewsWithIds.put(R.id.navigation_view, 13);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[8]
            , (androidx.appcompat.widget.AppCompatButton) bindings[7]
            , (androidx.appcompat.widget.AppCompatButton) bindings[6]
            , (androidx.appcompat.widget.AppCompatButton) bindings[10]
            , (androidx.appcompat.widget.AppCompatButton) bindings[9]
            , (androidx.drawerlayout.widget.DrawerLayout) bindings[0]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.LinearLayout) bindings[1]
            , (com.google.android.material.navigation.NavigationView) bindings[13]
            , (com.arlib.floatingsearchview.FloatingSearchView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (com.innomalist.taxi.common.components.WrapContentViewPager) bindings[11]
            , (com.google.android.material.tabs.TabLayout) bindings[12]
            );
        this.drawerLayout.setTag(null);
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