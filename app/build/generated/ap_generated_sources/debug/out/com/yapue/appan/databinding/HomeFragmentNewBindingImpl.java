package com.yapue.appan.databinding;
import com.yapue.appan.R;
import com.yapue.appan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeFragmentNewBindingImpl extends HomeFragmentNewBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ll, 1);
        sViewsWithIds.put(R.id.rl, 2);
        sViewsWithIds.put(R.id.action_bar_menus, 3);
        sViewsWithIds.put(R.id.openDrawer, 4);
        sViewsWithIds.put(R.id.tv_add_appointment, 5);
        sViewsWithIds.put(R.id.llCart, 6);
        sViewsWithIds.put(R.id.ivCart, 7);
        sViewsWithIds.put(R.id.tvCardcount, 8);
        sViewsWithIds.put(R.id.pager, 9);
        sViewsWithIds.put(R.id.indicator, 10);
        sViewsWithIds.put(R.id.rlSearch, 11);
        sViewsWithIds.put(R.id.IVsearch, 12);
        sViewsWithIds.put(R.id.adView, 13);
        sViewsWithIds.put(R.id.llVeterinarian, 14);
        sViewsWithIds.put(R.id.llPetShop, 15);
        sViewsWithIds.put(R.id.llPets_Grooming, 16);
        sViewsWithIds.put(R.id.llHostels, 17);
        sViewsWithIds.put(R.id.llTrainers, 18);
        sViewsWithIds.put(R.id.rv_animal_cat, 19);
        sViewsWithIds.put(R.id.rv_top_offers, 20);
        sViewsWithIds.put(R.id.pagerTopOffers, 21);
        sViewsWithIds.put(R.id.indicatorTopOffers, 22);
        sViewsWithIds.put(R.id.rv_products, 23);
        sViewsWithIds.put(R.id.rv_Top_brands_of_pet_food, 24);
        sViewsWithIds.put(R.id.llBreadInfo, 25);
        sViewsWithIds.put(R.id.ivType, 26);
        sViewsWithIds.put(R.id.ll_bg, 27);
        sViewsWithIds.put(R.id.ctvbType, 28);
        sViewsWithIds.put(R.id.rv_newProducts, 29);
        sViewsWithIds.put(R.id.tvmain, 30);
        sViewsWithIds.put(R.id.ctvName, 31);
        sViewsWithIds.put(R.id.ctvEmail, 32);
        sViewsWithIds.put(R.id.ctvNumber, 33);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeFragmentNewBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds));
    }
    private HomeFragmentNewBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.yapue.appan.utils.CustomEditText) bindings[12]
            , (android.widget.RelativeLayout) bindings[3]
            , (com.google.android.gms.ads.AdView) bindings[13]
            , (com.yapue.appan.utils.CustomTextView) bindings[32]
            , (com.yapue.appan.utils.CustomTextView) bindings[31]
            , (com.yapue.appan.utils.CustomTextView) bindings[33]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[28]
            , (com.viewpagerindicator.LinePageIndicator) bindings[10]
            , (com.viewpagerindicator.LinePageIndicator) bindings[22]
            , (android.widget.ImageView) bindings[7]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[26]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.RelativeLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.ImageView) bindings[4]
            , (com.yapue.appan.utils.AutoScrollViewPager) bindings[9]
            , (com.yapue.appan.utils.AutoScrollViewPager) bindings[21]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.RelativeLayout) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[19]
            , (androidx.recyclerview.widget.RecyclerView) bindings[29]
            , (androidx.recyclerview.widget.RecyclerView) bindings[23]
            , (androidx.recyclerview.widget.RecyclerView) bindings[24]
            , (androidx.recyclerview.widget.RecyclerView) bindings[20]
            , (com.yapue.appan.utils.CustomTextView) bindings[5]
            , (com.yapue.appan.utils.CustomTextView) bindings[8]
            , (com.yapue.appan.utils.CustomTextViewBold) bindings[30]
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