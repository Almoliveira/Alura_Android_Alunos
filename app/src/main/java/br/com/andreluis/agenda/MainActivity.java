package br.com.andreluis.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.andreluis.agenda.dao.AlunoDAO;
import br.com.andreluis.agenda.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btFloat = (Button) findViewById(R.id.main_btFloat);
        //Buscar Banco
        carregarLista();

        btFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(MainActivity.this,
                        FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });


    }


    private void carregarLista(){
        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> alunos = dao.buscaAlunos();

        dao.close();


        ListView lvAlunos = (ListView) findViewById(R.id.lvAlunos);


        ArrayAdapter<Aluno> adpAlunos =
                new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        lvAlunos.setAdapter(adpAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarLista();
    }
}