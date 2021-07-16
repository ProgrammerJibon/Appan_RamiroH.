package com.yapue.appan.databinding;
import com.yapue.appan.R;
import com.yapue.appan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdapterProductsForAnimBindingImpl extends AdapterProductsForAnimBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlFoodList, 1);
        sViewsWithIds.put(R.id.rlImage, 2);
        sViewsWithIds.put(R.id.ivFoodPic, 3);
        sViewsWithIds.put(R.id.out_of_stock, 4);
        sViewsWithIds.put(R.id.llOff, 5);
        sViewsWithIds.put(R.id.ctvbDiscount, 6);
        sViewsWithIds.put(R.id.ctvbFoodTitle, 7);
        sViewsWithIds.put(R.id.ctvFoodDesc, 8);
        sViewsWithIds.put(R.id.ctvWeight, 9);
        sViewsWithIds.put(R.id.ctvColor, 10);
        sViewsWithIds.put(R.id.ctvSize, 11);
        sViewsWithIds.put(R.id.ctvFoodSKU, 12);
        sViewsWithIds.put(R.id.ctvbPrice, 13);
        sViewsWithIds.put(R.id.ctvbPrice1, 14);
        sViewsWithIds.put(R.id.ctvbCurrency, 15);
        sViewsWithIds.put(R.id.ctvbOld, 16);
        sViewsWithIds.put(R.id.ratingBar, 17);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdapterProductsForAnimBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private AdapterProductsForAnimBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.yapue.appan.utils.CustomTextView) bindings[10]
            , (com.yapue.appan.utils.CustomTextView) bindings[8]
            , (com.yapue.appan.utils.CustomTextView) bindings[12]
            , (com.yapue.appan.utils.CustomTextView) bindings[11]
            , (com.yapue.appan.utils.CustomTextView) bindings[9]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[15]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[6]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[7]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[16]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[13]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[14]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.ImageView) bindings[4]
            , (me.zhanghai.android.materialratingbar.MaterialRatingBar) bindings[17]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.RelativeLayout) bindings[2]
            );
        this.llProduct.setTag(null);
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