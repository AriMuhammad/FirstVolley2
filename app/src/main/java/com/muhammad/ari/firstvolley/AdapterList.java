package com.muhammad.ari.firstvolley;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import static com.muhammad.ari.firstvolley.Url.Url.urlHDImage;
import static com.muhammad.ari.firstvolley.Url.Url.urlImage;

/**
 * Created by Ari on 22/02/2017.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {


    Context context;
    ArrayList<HashMap<String, String>> list;


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardList;
        TextView txtNama, txtProfesi;
        ImageView ivGambar;

        public ViewHolder(View itemView) {
            super(itemView);

            cardList = (CardView) itemView.findViewById(R.id.card_list);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtProfesi = (TextView) itemView.findViewById(R.id.txt_profesi);
            ivGambar = (ImageView) itemView.findViewById(R.id.iv_gambar);
        }
    }

    public AdapterList(ListActivity listActivity, ArrayList<HashMap<String, String>> list) {
        this.context = listActivity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //untuk menumpuk RecyclerView dengan layout yang ada pada list_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context)
                .load(urlHDImage + list.get(position).get("hd"))
                .into(holder.ivGambar);
        holder.txtNama.setText(list.get(position).get("nama"));
        holder.txtProfesi.setText(list.get(position).get("profesi"));
        holder.cardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "" + list.get(position).get("id"), Toast.LENGTH_SHORT).show();

//                ====== put extra objek menuju detail =====
                Intent goToDetail = new Intent(v.getContext(), Detail2Activity.class);
                goToDetail.putExtra("nama", list.get(position).get("nama"));
                goToDetail.putExtra("gambar", list.get(position).get("gambar"));
                goToDetail.putExtra("hd", list.get(position).get("hd"));
                goToDetail.putExtra("profesi", list.get(position).get("profesi"));
                goToDetail.putExtra("keterangan", list.get(position).get("keterangan"));
                v.getContext().startActivity(goToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
