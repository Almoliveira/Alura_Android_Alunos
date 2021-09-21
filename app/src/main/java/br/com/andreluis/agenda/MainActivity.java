package br.com.andreluis.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.andreluis.agenda.dao.AlunoDAO;
import br.com.andreluis.agenda.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView lvAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAlunos = (ListView) findViewById(R.id.lvAlunos);
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

      lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(i);
              Intent vaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
               vaiProFormulario.putExtra("aluno",aluno);
                startActivity(vaiProFormulario);
          }
      });

//      lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//          @Override
//          public boolean onItemLongClick(AdapterView<?> lista, View item, int i, long l) {
//
//              Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(i);
//              Toast.makeText(MainActivity.this, "Aluno " + aluno.getNome()
//                      + " Click Longo!" , Toast.LENGTH_SHORT).show();
//              return false;
//          }
//      });

        registerForContextMenu(lvAlunos);

    }


    private void carregarLista(){
        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> alunos = dao.buscaAlunos();

        dao.close();

        ArrayAdapter<Aluno> adpAlunos =
                new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        lvAlunos.setAdapter(adpAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)  {
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.deleta(aluno);
                dao.close();
                Toast.makeText(MainActivity.this,
                        "Deletar o aluno " + aluno.getNome(), Toast.LENGTH_SHORT).show();

                carregarLista();
                return false;
            }
        });
    }


}