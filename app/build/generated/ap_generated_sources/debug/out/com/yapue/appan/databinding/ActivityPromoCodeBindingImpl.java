package com.yapue.appan.databinding;
import com.yapue.appan.R;
import com.yapue.appan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPromoCodeBindingImpl extends ActivityPromoCodeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.action_bar_menus, 1);
        sViewsWithIds.put(R.id.back, 2);
        sViewsWithIds.put(R.id.tv_heading_text, 3);
        sViewsWithIds.put(R.id.right, 4);
        sViewsWithIds.put(R.id.rlSearch, 5);
        sViewsWithIds.put(R.id.etPromoCode, 6);
        sViewsWithIds.put(R.id.tvApply, 7);
        sViewsWithIds.put(R.id.svPromo, 8);
        sViewsWithIds.put(R.id.viewSeparator, 9);
        sViewsWithIds.put(R.id.flPromoList, 10);
        sViewsWithIds.put(R.id.rvPromoCodeList, 11);
        sViewsWithIds.put(R.id.tvNo, 12);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPromoCodeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityPromoCodeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[2]
            , (com.yapue.appan.utils.CustomEditText) bindings[6]
            , (android.widget.FrameLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.RelativeLayout) bindings[5]
            , (androidx.recyclerview.widget.RecyclerView) bindings[11]
            , (androidx.appcompat.widget.SearchView) bindings[8]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[7]
            , (com.yapue.appan.utils.CustomTextView) bindings[3]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[12]
            , (android.view.View) bindings[9]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
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