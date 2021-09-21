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

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        edtNome = (EditText) activity.findViewById(R.id.formulario_edtNome);
        edtEndereco = (EditText) activity.findViewById(R.id.formulario_edtEndereco);
        edtSite = (EditText) activity.findViewById(R.id.formulario_edtSite);
        edtTelefone = (EditText) activity.findViewById(R.id.formulario_edtTelefone);
        rbNota = (RatingBar) activity.findViewById(R.id.formulario_rbNota);

        aluno = new Aluno();

    }

    public Aluno getAluno() {

        aluno.setNome(this.edtNome.getText().toString());
        aluno.setEndereco(this.edtEndereco.getText().toString());
        aluno.setSite(this.edtSite.getText().toString());
        aluno.setTelefone(this.edtTelefone.getText().toString());
        aluno.setNota(Double.valueOf(this.rbNota.getProgress()));
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        edtNome.setText(aluno.getNome());
        edtEndereco.setText(aluno.getEndereco());
        edtSite.setText(aluno.getSite());
        edtTelefone.setText(aluno.getTelefone());
        rbNota.setProgress((int) aluno.getNota());

        this.aluno = aluno;
    }
}
