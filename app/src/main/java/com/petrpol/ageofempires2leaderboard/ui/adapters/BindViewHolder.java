package com.petrpol.ageofempires2leaderboard.ui.adapters;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.petrpol.ageofempires2leaderboard.BR;

/** View holder with abstract binding
 *  Used for all recycler views */
public class BindViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding mBinding;

    /** Default constructor with binding object*/
    public BindViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /** Binds object to view */
    public void bindObject (Object item){
        mBinding.setVariable(BR.item, item);
        mBinding.executePendingBindings();
    }

    /** Binds listener to view */
    public void bindOListener (Object listener){
        mBinding.setVariable(BR.listener, listener);
        mBinding.executePendingBindings();
    }
}
