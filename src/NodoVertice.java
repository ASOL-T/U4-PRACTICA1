/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alan
 */
public class NodoVertice {

    char dato;
    NodoVertice sig;
    NodoVertice ant;
    NodoArista arista;

    public NodoVertice(char d) {
        dato = d;
        sig = ant = null;
        arista = null;
    }

    public boolean insertarArista(NodoVertice direccion) {
        NodoArista nuevo = new NodoArista(direccion);
        if (nuevo == null) {
            return false;
        }

        if (arista == null) {
            arista = nuevo;
            return true;
        }
        irUltmo();
        arista.abajo = nuevo;
        arista.arriba = arista;
        return true;
    }

    private void irUltmo() {
        while (arista.abajo != null) {
            arista = arista.abajo;
        }
    }

    private void irPrimero() {
        while (arista.arriba != null) {
            arista = arista.arriba;
        }
    }

    public NodoArista buscarArista(NodoVertice direccion) {
        irPrimero();
        for (NodoArista buscar = arista; buscar != null; buscar = buscar.abajo) {
            if (buscar.direccion == direccion) {
                return buscar;
            }
        }
        return null;
    }
    
     public boolean buscarAristaB(NodoVertice direccion) {
        //irPrimero();
        for (NodoArista buscar = arista; buscar != null; buscar = buscar.abajo) {
            if (buscar.direccion == direccion) {
                return true;
            }
        }
        return false;
    }

    private boolean unaSolaArista() {
        return arista.abajo == null && arista.arriba == null;
    }

    public boolean eliminarArista(NodoVertice direccion) {
        if (arista == null) {
            return false;
        }
        NodoArista temp = buscarArista(direccion);
        if (temp == null) {
            return false;
        }
        if (temp == arista) {
            if (unaSolaArista()) {
                arista = null;
            } else {
                arista = arista.abajo;
                temp.abajo.arriba = temp.abajo = null;
            }
            return true;
        }
        if (temp.abajo == null) {
            temp.arriba.abajo = temp.abajo = null;
            return true;
        }
        temp.arriba.abajo = temp.abajo;
        temp.abajo.arriba = temp.arriba;
        temp.abajo = temp.arriba = null;
        return true;
    }
}
