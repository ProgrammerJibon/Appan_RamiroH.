package com.yapue.appan.databinding;
import com.yapue.appan.R;
import com.yapue.appan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class NearByFragBindingImpl extends NearByFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.action_bar_menus, 1);
        sViewsWithIds.put(R.id.back, 2);
        sViewsWithIds.put(R.id.action_add_appointment_back, 3);
        sViewsWithIds.put(R.id.tv_heading_text, 4);
        sViewsWithIds.put(R.id.IVfilter, 5);
        sViewsWithIds.put(R.id.viewSeparator, 6);
        sViewsWithIds.put(R.id.cardClick2, 7);
        sViewsWithIds.put(R.id.ivShop2, 8);
        sViewsWithIds.put(R.id.llVat, 9);
        sViewsWithIds.put(R.id.desc2, 10);
        sViewsWithIds.put(R.id.btnMoreVet, 11);
        sViewsWithIds.put(R.id.cardClick1, 12);
        sViewsWithIds.put(R.id.rlShop, 13);
        sViewsWithIds.put(R.id.ivShop, 14);
        sViewsWithIds.put(R.id.llPet, 15);
        sViewsWithIds.put(R.id.desc, 16);
        sViewsWithIds.put(R.id.btnMoreShop, 17);
        sViewsWithIds.put(R.id.cardClick3, 18);
        sViewsWithIds.put(R.id.ivShop3, 19);
        sViewsWithIds.put(R.id.llSalon, 20);
        sViewsWithIds.put(R.id.desc3, 21);
        sViewsWithIds.put(R.id.btnMoreGrooming, 22);
        sViewsWithIds.put(R.id.cardClick4, 23);
        sViewsWithIds.put(R.id.ivShop4, 24);
        sViewsWithIds.put(R.id.llHostal, 25);
        sViewsWithIds.put(R.id.desc4, 26);
        sViewsWithIds.put(R.id.btnMoreHostel, 27);
        sViewsWithIds.put(R.id.cardClick5, 28);
        sViewsWithIds.put(R.id.ivShop5, 29);
        sViewsWithIds.put(R.id.lltrainer, 30);
        sViewsWithIds.put(R.id.desc5, 31);
        sViewsWithIds.put(R.id.btnMoreTrainers, 32);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public NearByFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }
    private NearByFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[5]
            , (com.yapue.appan.utils.CustomTextView) bindings[3]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[2]
            , (com.yapue.appan.utils.CustomTextView) bindings[22]
            , (com.yapue.appan.utils.CustomTextView) bindings[27]
            , (com.yapue.appan.utils.CustomTextView) bindings[17]
            , (com.yapue.appan.utils.CustomTextView) bindings[32]
            , (com.yapue.appan.utils.CustomTextView) bindings[11]
            , (androidx.cardview.widget.CardView) bindings[12]
            , (androidx.cardview.widget.CardView) bindings[7]
            , (androidx.cardview.widget.CardView) bindings[18]
            , (androidx.cardview.widget.CardView) bindings[23]
            , (androidx.cardview.widget.CardView) bindings[28]
            , (com.yapue.appan.utils.CustomTextView) bindings[16]
            , (com.yapue.appan.utils.CustomTextView) bindings[10]
            , (com.yapue.appan.utils.CustomTextView) bindings[21]
            , (com.yapue.appan.utils.CustomTextView) bindings[26]
            , (com.yapue.appan.utils.CustomTextView) bindings[31]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[24]
            , (android.widget.ImageView) bindings[29]
            , (com.yapue.appan.utils.CustomTextView) bindings[25]
            , (com.yapue.appan.utils.CustomTextView) bindings[15]
            , (com.yapue.appan.utils.CustomTextView) bindings[20]
            , (com.yapue.appan.utils.CustomTextView) bindings[9]
            , (com.yapue.appan.utils.CustomTextView) bindings[30]
            , (android.widget.RelativeLayout) bindings[13]
            , (com.yapue.appan.utils.CustomTextView) bindings[4]
            , (android.view.View) bindings[6]
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