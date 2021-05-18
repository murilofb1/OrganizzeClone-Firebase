package com.murilofb.organizzeclone.helpers;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.murilofb.organizzeclone.models.Movimentacao;

public class FireBaseHelper {
    private static FirebaseAuth auth;
    private static DatabaseReference rootChild;
    private static DatabaseReference userReference;
    private static DatabaseReference transactionReference;


    private static final String SUCCESS_MSG = "Cadastro efetuado com sucesso";
    private static String FAILURE_MSG = "";

    private static final String CHILD_MOVIMENTACAO = "movimentacao";
    private static final String CHILD_USERS = "users";
    private static final String CHILD_DESPESA = "despesaTotal";
    private static final String CHILD_RECEITA = "receitaTotal";

    public static final String TIPO_DESPESA = "d";
    public static final String TIPO_RECEITA = "r";


    public static FirebaseAuth getFirebaseAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static void deslogar() {
        getFirebaseAuth().signOut();
    }

    public static DatabaseReference getRootRealtimeDatabase() {
        if (rootChild == null) {
            rootChild = FirebaseDatabase.getInstance().getReference();
        }
        return rootChild;
    }

    public static DatabaseReference getCurrentUserReference() {
        if (userReference == null) {
            String userUID = getFirebaseAuth().getCurrentUser().getUid();
            userReference = getRootRealtimeDatabase().child(CHILD_USERS).child(userUID);
        }
        return userReference;
    }

    public static DatabaseReference getUserTransactionsReference() {
        if (transactionReference == null) {
            String userUID = getFirebaseAuth().getUid();
            transactionReference = getRootRealtimeDatabase().child(CHILD_MOVIMENTACAO).child(userUID);
        }
        return transactionReference;
    }

    public static void alterarReceita(double valor) {
        getCurrentUserReference().child(CHILD_RECEITA).setValue(valor);
    }

    public static void alterarDespesa(double valor) {
        getCurrentUserReference().child(CHILD_DESPESA).setValue(valor);
    }

    public static void cadastrarUsuario(final Activity activity, final Movimentacao.Usuario usuario) {
        final Toast tst = Toast.makeText(activity, "", Toast.LENGTH_SHORT);

        getFirebaseAuth().createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String currentUID = getFirebaseAuth().getCurrentUser().getUid();
                            getRootRealtimeDatabase().child("users").child(currentUID).setValue(usuario);
                            tst.setText(SUCCESS_MSG);
                            tst.show();
                            activity.finish();

                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                FAILURE_MSG = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                FAILURE_MSG = "Digite um formato de e-mail válido!";
                            } catch (FirebaseAuthUserCollisionException e) {
                                FAILURE_MSG = "Usuário já cadastrado";
                            } catch (Exception e) {
                                FAILURE_MSG = "Erro: " + e.getMessage();
                                e.printStackTrace();
                            }
                            tst.setText(FAILURE_MSG);
                            tst.show();
                        }

                    }
                });
    }

    public static void logarUsuario(final Activity activity, final String email, final String senha) {
        final Toast tst = Toast.makeText(activity, "", Toast.LENGTH_SHORT);
        getFirebaseAuth().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            tst.setText("Login efetuado com sucesso");
                            tst.show();
                            activity.finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                FAILURE_MSG = "Insira corretamente o e-mail e a senha";
                            } catch (FirebaseAuthInvalidUserException e) {
                                FAILURE_MSG = "A conta não existe ou foi desativada";
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            tst.setText(FAILURE_MSG);
                            tst.show();
                        }

                    }
                });

    }


    public static void cadastrarMovimentacao(final Movimentacao mov, double valorAtual) {
        String currentUserUid = getFirebaseAuth().getCurrentUser().getUid();
        String mesAnos = DateCustom.mesAnosDataEscolhida(mov.getData());
        double novoValor = valorAtual + mov.getValor();

        getRootRealtimeDatabase()
                .child(CHILD_MOVIMENTACAO)
                .child(currentUserUid)
                .child(mesAnos)
                .push()
                .setValue(mov);

        if (mov.getTipo().equals(TIPO_DESPESA)) {
            String childDespesaTotal = "despesaTotal";
            getCurrentUserReference().child(childDespesaTotal).setValue(novoValor);
        } else if (mov.getTipo().equals(TIPO_RECEITA)) {
            String childReceitaTotal = "receitaTotal";
            getCurrentUserReference().child(childReceitaTotal).setValue(novoValor);
        }


    }
}