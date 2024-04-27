package com.example.app7_new.UI.UI.View;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.app7_new.R;
import com.example.app7_new.UI.UI.ViewModel.BooksListViewModel;
import com.example.app7_new.data.Model.Item;

public class ListFragment extends Fragment {
    public static class YourCustomRecyclerViewAdapter extends RecyclerView.Adapter<YourCustomRecyclerViewAdapter.ViewHolder> {
        private List<Item> dataList;
        private OnItemClicked onClick;
        public interface OnItemClicked {
            void onItemClick(int position);
        }
        public YourCustomRecyclerViewAdapter(List<Item> dataList) {
            this.dataList = dataList;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Item item = dataList.get(position);
            holder.textView.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        @Override
        public int getItemCount() {
            return dataList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
            }
        }
        public void setOnClick(OnItemClicked onClick){
            this.onClick=onClick;
        }
    }
    public ListFragment() {
        super(R.layout.fragment_list);
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void crossfade() {
        ImageView imageView = requireView().findViewById(R.id.imageView);
        if (imageView != null) { // Проверяем, что imageView не равен null
            // Устанавливаем начальное альфа-значение элемента imageView на 0 и делаем его видимым
            imageView.setAlpha(0f); // Установка начального значения прозрачности
            imageView.setVisibility(View.VISIBLE); // Установка видимости

            imageView.animate()
                    .alpha(1f) // Установка конечного значения прозрачности
                    .setDuration(500) // Установка длительности анимации
                    .setListener(null); // Установка слушателя на null, если не нужно обрабатывать завершение анимации
        }
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NavController navController = Navigation.findNavController(view);
        Button addNewBook_button = view.findViewById(R.id.add_new_book);
        Button viewPhoto = view.findViewById(R.id.view_photo);
        RecyclerView itemsList = view.findViewById(R.id.recycle_view);
        ImageView imageView = view.findViewById(R.id.imageView);
        BooksListViewModel booksViewModel = new ViewModelProvider(getActivity()).get(BooksListViewModel.class);

        booksViewModel.getUIState().observe(getViewLifecycleOwner(), uiState -> {
            List<Item> items = uiState.getBooksPositions();

            if (items == null || items.size() == 0) {
                itemsList.setVisibility(View.GONE);
            } else {
                if (itemsList.getVisibility() == View.GONE)
                    itemsList.setVisibility(View.VISIBLE);

                YourCustomRecyclerViewAdapter adapter = new YourCustomRecyclerViewAdapter(items);
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(this.getContext().getApplicationContext());
                itemsList.setLayoutManager(layoutManager);

                itemsList.setAdapter(adapter);
            }
        });

        addNewBook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(view).navigate(R.id.action_listFragment_to_createBookFragment);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_out,
                                0,
                                0,
                                0
                        )
                        .replace(R.id.nav_host_fragment, new CreateBookFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        viewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(view).navigate(R.id.action_listFragment_to_createBookFragment);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new FragmentPhoto1())
                        .addToBackStack(null)
                        .commit();
            }
        });
        crossfade();
    }
}