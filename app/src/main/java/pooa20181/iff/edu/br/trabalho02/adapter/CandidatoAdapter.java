package pooa20181.iff.edu.br.trabalho02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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


            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_candidato_cv, parent, false);
                CandidatoViewHolder candidatoViewHolder = new CandidatoViewHolder(view);

                        return candidatoViewHolder;
            }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

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

                        nomeCandidato = (TextView) itemView.findViewById(R.id.tvNome);
                        partido = (TextView) itemView.findViewById(R.id.tvPartido);
                        numeroUrna = (TextView) itemView.findViewById(R.id.tvNumeroUrna);
                        cargo = (TextView) itemView.findViewById(R.id.tvSecao);

                        txtNomeCandidato = (TextView) itemView.findViewById(R.id.textNome);
                        txtPartido = (TextView) itemView.findViewById(R.id.textPartido);
                        txtNumeroUrna = (TextView) itemView.findViewById(R.id.textNumeroUrna);
                        txtCargo = (TextView) itemView.findViewById(R.id.textCargo);

                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clickRecyclerViewListener.onClick(candidatos.get(getLayoutPosition()));
                            }
                        });
                }
            }

        }