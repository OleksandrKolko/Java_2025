package HW_8;

public class B08_01 {
    public static class RecursiveStack<T> {

        private final T head;
        private final RecursiveStack<T> tail;

        public RecursiveStack() {
            this.head = null;
            this.tail = null;
        }

        private RecursiveStack(T head, RecursiveStack<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public boolean isEmpty() {
            return head == null && tail == null;
        }

        public RecursiveStack<T> push(T value) {
            return new RecursiveStack<>(value, this);
        }

        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Стек порожній");
            }
            return head;
        }

        public RecursiveStack<T> pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Стек порожній");
            }
            return tail;
        }
    }
    public static void main(String[] args) {
        RecursiveStack<Integer> stack = new RecursiveStack<>();

        stack = stack.push(10);
        stack = stack.push(20);
        stack = stack.push(30);

        System.out.println("Верхній елемент: " + stack.peek());

        stack = stack.pop();
        System.out.println("Новий верхній: " + stack.peek());
    }
}