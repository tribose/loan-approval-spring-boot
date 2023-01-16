package practice.stack;

import java.util.Stack;

public class QueueUsingTwoStacks {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void makeQueueUsingStack(Integer a) {
        while(!s1.empty()) {
            s2.push(s1.pop());
        }

        s1.push(a);

        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public Stack<Integer> getS1() {
        return s1;
    }

    public static void main(String[] args) {

        QueueUsingTwoStacks stacks = new QueueUsingTwoStacks();
        stacks.makeQueueUsingStack(1);
        stacks.makeQueueUsingStack(2);
        stacks.makeQueueUsingStack(3);

        System.out.println(stacks.getS1());
    }
}
