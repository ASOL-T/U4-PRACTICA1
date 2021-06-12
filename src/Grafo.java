/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alan
 */
public class Grafo {

    NodoVertice vertice;

    public Grafo() {
        vertice = null;
    }

    public boolean insertar(char dato) {
        NodoVertice nuevo = new NodoVertice(dato);
        if (nuevo == null) {
            return false;
        }
        if (vertice == null) {
            vertice = nuevo;
            return true;
        }
        irUltimo();
        vertice.sig = nuevo;
        nuevo.ant = vertice;
        return true;
    }

    private void irUltimo() {
        while (vertice.sig != null) {
            vertice = vertice.sig;
        }
    }

    private void irPrimero() {
        while (vertice.ant != null) {
            vertice = vertice.ant;
        }
    }

    private NodoVertice buscarVertice(char dato) {
        if (vertice == null) {
            return null;
        }
        irPrimero();
        for (NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig) {
            if (buscar.dato == dato) {
                return buscar;
            }
        }
        return null;
    }

    public boolean insertarArista(char origen, char destino) {
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);

        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }
        return nodoOrigen.insertarArista(nodoDestino);
    }

    public boolean eliminarArista(char origen, char destino) {
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }
        return nodoOrigen.eliminarArista(nodoDestino);
    }

    public boolean unSoloVertice() {
        return vertice.ant == null && vertice.sig == null;
    }

    public boolean eliminarVertice(char dato) {
        if (vertice == null) {
            return false;
        }
        NodoVertice temp = buscarVertice(dato);
        if (temp == null) {
            return false;
        }
        if (temp.arista != null) {
            return false;
        }
        quitarAristaDeOtrosVertices(temp);
        if (temp == vertice) {
            if (unSoloVertice()) {
                vertice = null;
            } else {
                vertice = temp;
                temp.sig.ant = temp.sig = null;
            }
            return true;
        }
        if (temp.sig == null) {
            temp.ant.sig = temp.ant = null;
            return true;
        }
        temp.ant.sig = temp.sig;
        temp.sig.ant = temp.ant;
        temp.sig = temp.ant = null;
        return true;
    }

    public String matrizAdyacencia() {
        String cad = "";
        for (NodoVertice i = vertice; i != null; i = i.sig) {
            for (NodoVertice j = vertice; j != null; j = j.sig) {
                if (i.buscarAristaB(j)) {
                    cad += "1" + " ";
                } else {
                    cad += "0" + " ";
                }
            }
            cad += "\n";
        }
        return cad;
    }

    public String listaAdyacencia(char dato) {
        NodoVertice temp = buscarVertice(dato);
        if (temp == null) {
            return "NO EXISTE VERTICE";
        }
        String cad = "";
        for (NodoVertice i = vertice; i != null; i = i.sig) {
            if (temp.buscarAristaB(i)) {
                cad += "1" + " ";
            } else {
                cad += "0" + " ";
            }
        }
        return cad;
    }

    public boolean buscarCamino(String camino) {
        String vector[] = camino.split(",");

        NodoVertice inicio = buscarVertice(vector[0].charAt(0));
        if (inicio == null) {
            return false;
        }
        for (int i = 1; i < vector.length; i++) {
            if (inicio == null) {
                return false;
            }
            if (inicio.buscarAristaB(new NodoVertice(vector[i].charAt(0)))) {
                inicio =buscarVertice(vector[i].charAt(0));
            } else {
                return false;
            }
        }
        return true;
    }

    private void quitarAristaDeOtrosVertices(NodoVertice nodoEliminar) {
        irPrimero();
        for (NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig) {
            buscar.eliminarArista(nodoEliminar);
        }
    }
}
