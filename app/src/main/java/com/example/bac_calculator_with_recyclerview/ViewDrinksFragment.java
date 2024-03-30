package com.example.bac_calculator_with_recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.bac_calculator_with_recyclerview.databinding.DrinkRowItemBinding;
import com.example.bac_calculator_with_recyclerview.databinding.FragmentViewDrinksBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class ViewDrinksFragment extends Fragment {
    private static final String ARG_PARAM_DRINKS = "ARG_PARAM_DRINKS";
    private ArrayList<Drink> mDrinks;
    public ViewDrinksFragment() {
        // Required empty public constructor
    }

    public static ViewDrinksFragment newInstance(ArrayList<Drink> drinks) {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DRINKS, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDrinks = (ArrayList<Drink>) getArguments().getSerializable(ARG_PARAM_DRINKS);
        }
    }

    FragmentViewDrinksBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Drinks");

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeViewDrinks();
            }
        });

        binding.imageViewSortAscAlcohol.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                mDrinks.sort(new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink1, Drink drink2) {
                        return (int) (drink1.getAlcohol() - drink2.getAlcohol());
                    }
                });
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            }
        });

        binding.imageViewSortDescAlcohol.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                mDrinks.sort(new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink1, Drink drink2) {
                        return (int) ((-1) * drink1.getAlcohol() - drink2.getAlcohol());
                    }
                });
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            }
        });

        binding.imageViewSortAscDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                mDrinks.sort(new Comparator<Drink>() {
                    @Override
                    public int compare(Drink date1, Drink date2) {
                        return date1.getAddedOn().compareTo(date2.getAddedOn());
                    }
                });
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            }
        });

        binding.imageViewSortDescDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                mDrinks.sort(new Comparator<Drink>() {
                    @Override
                    public int compare(Drink date1, Drink date2) {
                        return date2.getAddedOn().compareTo(date1.getAddedOn());
                    }
                });
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new DrinkAdapter());
    }

    ViewDrinksFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ViewDrinksFragmentListener) context;
    }

    interface ViewDrinksFragmentListener{
        void closeViewDrinks();
    }

    class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

        @NonNull
        @Override
        public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            DrinkRowItemBinding binding = DrinkRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {
            Drink drink = mDrinks.get(position);
            holder.bind(drink);
        }

        @Override
        public int getItemCount() {
            return mDrinks.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            DrinkRowItemBinding mBinding;
            public ViewHolder(DrinkRowItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            @SuppressLint("SetTextI18n")
            public void bind(Drink drink) {
                mBinding.textViewAlcoholPercentage.setText(drink.getAlcohol() + "%");
                mBinding.textViewAlcoholSize.setText(drink.getSize() + " oz");
                mBinding.textViewDateAdded.setText(drink.getAddedOn().toString());

                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(View v) {
                        mDrinks.remove(getAdapterPosition());
                        if(mDrinks.isEmpty()){
                            mListener.closeViewDrinks();
                        }else {
                            notifyDataSetChanged();
                        }
                    }
                });


            }
        }
    }

}