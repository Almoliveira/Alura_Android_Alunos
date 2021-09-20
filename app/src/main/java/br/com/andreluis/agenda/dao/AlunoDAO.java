package br.com.andreluis.agenda.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.andreluis.agenda.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {


    public AlunoDAO(@Nullable Context context) {


        super(context, "Agemda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE ALUNOS (" +
                " id INTEGER PRIMARY KEY, " +
                " nome TEXT NOT NULL, " +
                " endereco TEXT, " +
                " telefone TEXT, " +
                " site TEXT, "+
                " nota REAL );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS ALUNOS";
        db.execSQL(sql);
        onCreate(db);
    }

    public void Insere(Aluno aluno) {

        final SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nome", aluno.getNome());
        cv.put("endereco", aluno.getEndereco());
        cv.put("telefone", aluno.getTelefone());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());

        db.insert("ALUNOS", null, cv);
    }

    public List<Aluno> buscaAlunos()  {
        try {
            String sql = "SELECT * FROM Alunos;";
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(sql, null);

            List<Aluno> alunos = new ArrayList<Aluno>();
            while (c.moveToNext()) {
                Aluno aluno = new Aluno();
                aluno.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                aluno.setNome(c.getString(c.getColumnIndexOrThrow("nome")));
                aluno.setEndereco(c.getString(c.getColumnIndexOrThrow("endereco")));
                aluno.setTelefone(c.getString(c.getColumnIndexOrThrow("telefone")));
                aluno.setSite(c.getString(c.getColumnIndexOrThrow("site")));
                aluno.setNota(c.getDouble(c.getColumnIndexOrThrow("nota")));
                alunos.add(aluno);
            }
            c.close();

            db.close();

            return alunos;
        }catch (CursorIndexOutOfBoundsException er) {
            throw er;
        }

    }
}
