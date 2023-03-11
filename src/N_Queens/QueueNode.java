package N_Queens;

import N_Queens.Node;
import N_Queens.QueueNode;

class QueueNode{
	// classe auxiliaire pour la file d'attente
    protected Node key;
    protected QueueNode next;

    public QueueNode(Node key){

        this.key = key;
        this.next = null;
    }
    public QueueNode(){
        this.key = null;
        this.next = null;
    }
}