package br.com.andreluis.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.andreluis.agenda.modelo.Aluno;

public class FormularioHelper {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtSite;
    private EditText edtTelefone;
    private RatingBar rbNota;

    public FormularioHelper(FormularioActivity activity){
        this.edtNome = (EditText) activity.findViewById(R.id.formulario_edtNome);
        this.edtEndereco = (EditText) activity.findViewById(R.id.formulario_edtEndereco);
        this.edtSite = (EditText) activity.findViewById(R.id.formulario_edtSite);
        this.edtTelefone = (EditText) activity.findViewById(R.id.formulario_edtTelefone);
        this.rbNota = (RatingBar) activity.findViewById(R.id.formulario_rbNota);


    }

    public Aluno getAluno() {

        Aluno aluno = new Aluno();

        aluno.setNome(this.edtNome.getText().toString());
        aluno.setEndereco(this.edtEndereco.getText().toString());
        aluno.setSite(this.edtSite.getText().toString());
        aluno.setTelefone(this.edtTelefone.getText().toString());
        aluno.setNota(Double.valueOf(this.rbNota.getProgress()));
        return aluno;
    }
}
