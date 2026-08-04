package core;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomLinkedList<T> implements Iterable<T> {

  private Node<T> head;
  private int size;

  private static class Node<T> {

    final T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  public CustomLinkedList() {
    this.head = null;
    this.size = 0;
  }

  public Stream<T> stream() {
    return StreamSupport.stream(new LinkedListSpliterator<>(head, size), false);
  }

  public CustomLinkedList(Collection<? extends T> collection) {
    if (collection == null) {
      throw new IllegalArgumentException("Исходная коллекция не может быть null");
    }
    this.head = null;
    this.size = 0;
    for (T item : collection) {
      this.add(item);
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void add(T data) {
    insert(size, data);
  }

  public void insert(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
    }

    Node<T> newNode = new Node<>(data);

    if (index == 0) {
      newNode.next = head;
      head = newNode;
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      newNode.next = current.next;
      current.next = newNode;
    }
    size++;
  }

  public void addAll(CustomLinkedList<T> otherList) {
    if (otherList == null) {
      throw new IllegalArgumentException("Список не может быть null");
    }
    for (T item : otherList) {
      this.add(item);
    }
  }

  public T get(int index) {
    checkIndex(index);
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  public void remove(int index) {
    checkIndex(index);
    if (index == 0) {
      head = head.next;
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      current.next = current.next.next;
    }
    size--;
  }

  public void clear() {
    head = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = head;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T data = current.data;
        current = current.next;
        return data;
      }
    };
  }

  public Stream<T> parallelStream() {
    return StreamSupport.stream(new LinkedListSpliterator<>(head, size), true);
  }

  private static class LinkedListSpliterator<T> implements Spliterator<T> {

    private Node<T> current;
    private int estSize;

    LinkedListSpliterator(Node<T> startNode, int estSize) {
      this.current = startNode;
      this.estSize = estSize;
    }

    @Override
    public Spliterator<T> trySplit() {
      if (estSize <= 1 || current == null) {
        return null;
      }

      int mid = estSize / 2;
      Object[] a = new Object[mid];

      for (int i = 0; i < mid && current != null; i++) {
        a[i] = current.data;
        current = current.next;
        estSize--;
      }
      return Spliterators.spliterator(a, 0, mid, ORDERED | SIZED | SUBSIZED);
    }

    @Override
    public boolean tryAdvance(java.util.function.Consumer<? super T> action) {
      if (action == null) {
        throw new NullPointerException();
      }
      if (current != null && estSize > 0) {
        action.accept(current.data);
        current = current.next;
        estSize--;
        return true;
      }
      return false;
    }

    @Override
    public long estimateSize() {
      return estSize;
    }

    @Override
    public int characteristics() {
      return SIZED | ORDERED | SUBSIZED;
    }
  }
}