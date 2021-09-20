package br.com.andreluis.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

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
                aluno = this.helper.getAluno();
                Toast.makeText(FormularioActivity.this,
                        "Aluno " + aluno.getNome() + " salvo " +
                                "corretamente!", Toast.LENGTH_SHORT).show();
                AlunoDAO dao = new AlunoDAO(this);
                dao.Insere(aluno);
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