package pe.com.ricindigus.generadorinei.modelo.DataSourceCaptura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pe.com.ricindigus.generadorinei.componentes.componente_caratula.POJOCaratula;
import pe.com.ricindigus.generadorinei.componentes.componente_identificacion.POJOIdentificacion;
import pe.com.ricindigus.generadorinei.pojos.Marco;
import pe.com.ricindigus.generadorinei.pojos.Ubigeo;
import pe.com.ricindigus.generadorinei.pojos.Usuario;

/**
 * Created by dmorales on 13/12/2017.
 */

public class Data {
    Context contexto;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public Data(Context contexto) {
        this.contexto = contexto;
        sqLiteOpenHelper = new DBHelper(contexto);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteOpenHelper.close();
    }

    public long getNumeroItemsUbigeo(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,SQLConstantes.tableUbigeo);
    }
    public long getNumeroItemsUsuario(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,SQLConstantes.tableUsuarios);
    }
    public long getNumeroItemsMarco(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,SQLConstantes.tableMarco);
    }


    public Marco getMarco(String idEmpresa){
        Marco marco = new Marco();
        String[] whereArgs = new String[]{idEmpresa};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tableMarco,
                    SQLConstantes.ALL_COLUMNS_MARCO,SQLConstantes.WHERE_CLAUSE_ID_EMPRESA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                marco.setID(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ID)));
                marco.setRUC(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_RUC)));
                marco.setRAZON_SOCIAL(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_RAZON_SOCIAL)));
                marco.setNOMBRE_COMERCIAL(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_NOMBRE_COMERCIAL)));
                marco.setOPERADOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_OPERADOR)));
                marco.setJEFE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_JEFE)));
                marco.setOBSERVADOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_OBSERVADOR)));
                marco.setANIO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ANIO)));
                marco.setMES(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MES)));
                marco.setPERIODO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PERIODO)));
                marco.setCCDD(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCDD)));
                marco.setDEPARTAMENTO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_DEPARTAMENTO)));
                marco.setCCPP(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCPP)));
                marco.setPROVINCIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PROVINCIA)));
                marco.setCCDI(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCDI)));
                marco.setDISTRITO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_DISTRITO)));
                marco.setT_EMPRESA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_T_EMPRESA)));
                marco.setFRENTE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_FRENTE)));
                marco.setZONA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ZONA)));
                marco.setMANZANA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MANZANA)));
                marco.setCAT_VIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CAT_VIA)));
                marco.setNOM_VIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_NOM_VIA)));
                marco.setPUERTA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PUERTA)));
                marco.setINTERIOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_INTERIOR)));
                marco.setPISO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PISO)));
                marco.setMZ(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MZ)));
                marco.setLOTE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_LOTE)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return marco;
    }
    public ArrayList<Marco> getAllMarcos(String idUsuario, int permisoUsuario){
        ArrayList<Marco> marcos = new ArrayList<Marco>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            switch (permisoUsuario){
                case 1: cursor = sqLiteDatabase.query(SQLConstantes.tableMarco, SQLConstantes.ALL_COLUMNS_MARCO,
                        SQLConstantes.WHERE_CLAUSE_ID_OPERADOR,whereArgs,null,null,null);break;
                case 2: cursor = sqLiteDatabase.query(SQLConstantes.tableMarco, SQLConstantes.ALL_COLUMNS_MARCO,
                        SQLConstantes.WHERE_CLAUSE_ID_JEFE,whereArgs,null,null,null);break;
                case 3: cursor = sqLiteDatabase.query(SQLConstantes.tableMarco, SQLConstantes.ALL_COLUMNS_MARCO,
                        SQLConstantes.WHERE_CLAUSE_ID_OBSERVADOR,whereArgs,null,null,null);break;
            }

            while(cursor.moveToNext()){
                Marco marco = new Marco();
                marco.setID(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ID)));
                marco.setRUC(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_RUC)));
                marco.setRAZON_SOCIAL(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_RAZON_SOCIAL)));
                marco.setNOMBRE_COMERCIAL(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_NOMBRE_COMERCIAL)));
                marco.setOPERADOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_OPERADOR)));
                marco.setJEFE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_JEFE)));
                marco.setOBSERVADOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_OBSERVADOR)));
                marco.setANIO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ANIO)));
                marco.setMES(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MES)));
                marco.setPERIODO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PERIODO)));
                marco.setCCDD(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCDD)));
                marco.setDEPARTAMENTO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_DEPARTAMENTO)));
                marco.setCCPP(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCPP)));
                marco.setPROVINCIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PROVINCIA)));
                marco.setCCDI(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CCDI)));
                marco.setDISTRITO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_DISTRITO)));
                marco.setT_EMPRESA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_T_EMPRESA)));
                marco.setFRENTE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_FRENTE)));
                marco.setZONA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_ZONA)));
                marco.setMANZANA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MANZANA)));
                marco.setCAT_VIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_CAT_VIA)));
                marco.setNOM_VIA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_NOM_VIA)));
                marco.setPUERTA(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PUERTA)));
                marco.setINTERIOR(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_INTERIOR)));
                marco.setPISO(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_PISO)));
                marco.setMZ(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_MZ)));
                marco.setLOTE(cursor.getString(cursor.getColumnIndex(SQLConstantes.MARCO_LOTE)));
                marcos.add(marco);
            }
        }finally {
            if(cursor != null) cursor.close();
        }
        return marcos;
    }

    public void insertarMarco(Marco marco){
        ContentValues contentValues = marco.toValues();
        sqLiteDatabase.insert(SQLConstantes.tableMarco,null,contentValues);
    }
    public void InsertarMarcos(ArrayList<Marco> empresas){
        long items = getNumeroItemsMarco();
        if(items == 0){
            for (Marco empresa : empresas) {
                try {
                    insertarMarco(empresa);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //----------------------USUARIOS-----------------------------------------------------------------------------------------
    public void insertarUsuario(Usuario usuario){
        ContentValues contentValues = usuario.toValues();
        sqLiteDatabase.insert(SQLConstantes.tableUsuarios,null,contentValues);
    }
    public void insertarUsuarios(ArrayList<Usuario> usuarios){
        long items = getNumeroItemsUsuario();
        if(items == 0){
            for (Usuario usuario : usuarios) {
                try {
                    insertarUsuario(usuario);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public Usuario getUsuario(String idUsuario){
        Usuario usuario = new Usuario();
        String[] whereArgs = new String[]{idUsuario};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tableUsuarios,
                    SQLConstantes.ALL_COLUMNS_USUARIOS,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                usuario.setUSUARIO_ID(cursor.getString(cursor.getColumnIndex(SQLConstantes.USUARIO_ID)));
                usuario.setUSUARIO_PASSWORD(cursor.getString(cursor.getColumnIndex(SQLConstantes.USUARIO_PASSWORD)));
                usuario.setUSUARIO_PERMISO(cursor.getString(cursor.getColumnIndex(SQLConstantes.USUARIO_PERMISO)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return usuario;
    }
    //------------------FIN USUARIO-----------------------------------------------------------------------------------------------------

    //----------------------UBIGEOS-------------------------------------------------------------------------------------------------------
    public void insertarUbigeo(Ubigeo ubigeo){
        ContentValues contentValues = ubigeo.toValues();
        sqLiteDatabase.insert(SQLConstantes.tableUbigeo,null,contentValues);
    }
    public void insertarUbigeos(ArrayList<Ubigeo> ubigeos){
        long items = getNumeroItemsUbigeo();
        if(items == 0){
            for (Ubigeo ubigeo : ubigeos) {
                try {
                    insertarUbigeo(ubigeo);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public ArrayList<String> getUbigeos(String idUbi){
        ArrayList<String> ubigeos = new ArrayList<String>();
        ubigeos.add("Seleccione");
        String[] whereArgs = new String[]{idUbi};
        long items = getNumeroItemsUbigeo();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tableUbigeo,
                    SQLConstantes.ALL_COLUMNS_UBIGEOS,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                ubigeos.add(cursor.getString(cursor.getColumnIndex(SQLConstantes.UBIGEO_DISTRITO)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return ubigeos;
    }
    //------------------FIN UBIGEO-----------------------------------------------------------------------------------------------------



}
