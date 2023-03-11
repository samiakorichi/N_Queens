package N_Queens;

import N_Queens.Node;
import N_Queens.QueueNode;

public class Queue{
    //implementação simples de uma fila que utiliza lista encadeada

    private QueueNode _head;
    private QueueNode _tail;

    public Queue(){
        _head = new QueueNode();
        _tail  = _head;
    }

    public Node front(){
        return _head.next.key;
    }
    public void push(Node v){
        _tail.next = new QueueNode(v);
        _tail = _tail.next;
    }
    public boolean empty(){
        return _head.next == null;
    }

    public void pop(){
        if(_tail == _head.next){
            _tail = _head;
            _head.next = null;
        }else {
            _head.next = _head.next.next;
        }
    }
}