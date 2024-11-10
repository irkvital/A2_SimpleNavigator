package edu.school21;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.school21.dataStructures.Queue;

class QueueTests {
    @Test
    void testQueue() {

        System.out.println("-------QUEUE-------");
        Queue<Integer> queue = new Queue<>();
        queue.queue();
        Integer[] queueElems = initElems();

        for (int i = 0; i < queueElems.length; i++) {
            queue.push(queueElems[i]);
        }

        System.out.println("QUEUE SIZE = " + queue.getSize());
        Assertions.assertEquals(queueElems.length, queue.getSize());
        int i = 0;
        while (queue.getSize() > 0) {
            System.out.println("front element = " + queue.front());
            Assertions.assertEquals(queueElems[queueElems.length - 1], queue.front());

            System.out.println("back element = " + queue.back());
            Assertions.assertEquals(queueElems[i], queue.back());
            System.out.println("popped element = " + queue.pop() + "; queue size = " + queue.getSize());
            Assertions.assertEquals(queueElems.length - i - 1, queue.getSize());
            i++;
        }

    }

    private Integer[] initElems() {
        int randomStackLen = ThreadLocalRandom.current().nextInt(5, 10 + 1);
        Integer[] stackElems = new Integer[randomStackLen];

        for (int i = 0; i < stackElems.length; i++) {
            Integer randomStackElem = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
            stackElems[i] = randomStackElem;
        }
        System.out.println("ADDED FIRST...ADDED LAST");
        for (int i = 0; i < stackElems.length; i++) {
            System.out.print(stackElems[i] + " ");
        }
        System.out.println();

        return stackElems;
    }
}
