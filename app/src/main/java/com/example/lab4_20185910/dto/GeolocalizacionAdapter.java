package com.example.lab4_20185910.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20185910.R;
import com.example.lab4_20185910.dto.Geolocalizacion;

import java.util.List;

public class GeolocalizacionAdapter extends RecyclerView.Adapter<GeolocalizacionAdapter.GeolocationViewHolder> {

    private List<Geolocalizacion> listaGeolocalizacion;
    private Context context;

    private static String TAG = "msg-test-CiudadViewHolder";

    @NonNull
    @Override
    public GeolocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.irv_geolocalizacion, parent, false);
        return new GeolocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeolocationViewHolder holder, int position) {
        Geolocalizacion geolocalizacion = listaGeolocalizacion.get(position);
        holder.geolocation = geolocalizacion;

        TextView tvNombre = holder.itemView.findViewById(R.id.textViewNombre);
        TextView tvLatitud = holder.itemView.findViewById(R.id.textViewLatitud);
        TextView tvLongitud = holder.itemView.findViewById(R.id.textViewLongitud);

        tvNombre.setText(geolocalizacion.getNombre());
        tvLatitud.setText(geolocalizacion.getLatitud());
        tvLongitud.setText(String.valueOf(geolocalizacion.getLongitud()));
    }

    @Override
    public int getItemCount() {
        return listaGeolocalizacion.size();
    }

    public void set(Context context) {
    }

    public void setGelocalizacion(List<Geolocalizacion> geolocalizacion) {
    }

    public class GeolocationViewHolder extends RecyclerView.ViewHolder {

        Geolocalizacion geolocation;
        public GeolocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public List<Geolocalizacion> getListaGeolocalizacion() {
        return listaGeolocalizacion;
    }

    public void setListaGeolocalizacion(List<Geolocalizacion> listaGeolocalizacion) {
        this.listaGeolocalizacion = listaGeolocalizacion;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
