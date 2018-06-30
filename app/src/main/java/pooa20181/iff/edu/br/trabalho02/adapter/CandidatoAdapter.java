package pooa20181.iff.edu.br.trabalho02.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import pooa20181.iff.edu.br.trabalho02.R;
import pooa20181.iff.edu.br.trabalho02.model.Candidato;




public class CandidatoAdapter extends RecyclerView.Adapter{

    private List<Candidato> candidatos;
    private Context context;
    private static  ClickRecyclerViewListener clickRecyclerViewListener;

    public CandidatoAdapter(List<Candidato> candidatos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.candidatos = candidatos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_candidato_cv, parent, false);
        CandidatoViewHolder candidatoViewHolder = new CandidatoViewHolder(view);

        return candidatoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CandidatoViewHolder candidatoHolder = (CandidatoViewHolder) viewHolder;
        Candidato candidato = this.candidatos.get(position);

        candidatoHolder.nomeCandidato.setText(candidato.getNome());
        candidatoHolder.partido.setText(candidato.getPartido());
        candidatoHolder.numeroUrna.setText(candidato.getNumeroUrna());
        candidatoHolder.cargo.setText(candidato.getCargo());

        candidatoHolder.txtNomeCandidato.setText("Nome do Candidato: ");
        candidatoHolder.txtPartido.setText("Partido: ");
        candidatoHolder.txtCargo.setText("Cargo: ");
        candidatoHolder.txtNumeroUrna.setText("Número da Urna");
    }

    @Override
    public int getItemCount() {
        return candidatos.size();
    }

    public class CandidatoViewHolder extends RecyclerView.ViewHolder{

        private final TextView nomeCandidato;
        private final TextView partido;
        private final TextView numeroUrna;
        private final TextView cargo;

        private final TextView txtNomeCandidato;
        private final TextView txtPartido;
        private final TextView txtNumeroUrna;
        private final TextView txtCargo;

        public CandidatoViewHolder(View itemView){
            super(itemView);

            nomeCandidato = (TextView) itemView.findViewById(R.id.tvNomeEleitor);
            partido = (TextView) itemView.findViewById(R.id.tvNumeroTitulo);
            numeroUrna = (TextView) itemView.findViewById(R.id.tvZona);
            cargo = (TextView) itemView.findViewById(R.id.tvCargo);

            txtNomeCandidato = (TextView) itemView.findViewById(R.id.textNomeEleitor);
            txtPartido = (TextView) itemView.findViewById(R.id.textNumeroTitulo);
            txtNumeroUrna = (TextView) itemView.findViewById(R.id.textZona);
            txtCargo = (TextView) itemView.findViewById(R.id.textSecao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(candidatos.get(getLayoutPosition()));
                }
            });
        }
    }

}

