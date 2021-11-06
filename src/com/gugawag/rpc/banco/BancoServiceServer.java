package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;
    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        Optional<Conta> contaBuscada = contas.stream().filter(conta -> conta.getNumero().equals(numero))
                .findFirst();

        if (contaBuscada.isPresent()) {
            return contaBuscada.get().getSaldo();
        }

        return -1;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numero, Double saldo) throws RemoteException {
        contas.add(new Conta(numero, saldo));
    }

}
