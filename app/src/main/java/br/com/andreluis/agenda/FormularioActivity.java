package br.com.andreluis.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.andreluis.agenda.dao.AlunoDAO;
import br.com.andreluis.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    FormularioHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null){

            helper.preencheFormulario(aluno);
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.getAluno();

                AlunoDAO dao = new AlunoDAO(this);

                //Int sempre usa 0
                if(aluno.getId() != 0){
                    dao.altera(aluno);
                    Toast.makeText(FormularioActivity.this,
                            "Aluno " + aluno.getNome() + " atualizado " +
                                    "corretamente!", Toast.LENGTH_SHORT).show();

                } else {
                    dao.Insere(aluno);

                    Toast.makeText(FormularioActivity.this,
                            "Aluno " + aluno.getNome() + " salvo " +
                                    "corretamente!", Toast.LENGTH_SHORT).show();

                }

                dao.close();

                finish();
                break;
            default:
                Toast.makeText(FormularioActivity.this, "Função não existe",
                        Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}