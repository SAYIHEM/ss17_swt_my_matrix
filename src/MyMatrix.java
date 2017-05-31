import java.awt.*;
import java.util.*;
import java.util.List;

public class MyMatrix<T> implements Matrix<T> {

    private Map<Point, T> matrixEntries;

    public MyMatrix() {

        this.matrixEntries = new HashMap<>();
    }

    @Override
    public int getRowCount() {

        if (this.matrixEntries.isEmpty()) return 0;

        int count = 0;

        Set<Point> keySet = this.matrixEntries.keySet();

        for (Point p : keySet) {

            if (count < p.getX()) {

                count = (int) p.getX();
            }
        }

        return ++count;
    }

    @Override
    public int getColumnCount() {

        if (this.matrixEntries.isEmpty()) return 0;

        int count = 0;

        Set<Point> keySet = this.matrixEntries.keySet();

        for (Point p : keySet) {

            if (count < p.getY()) {

                count = (int) p.getY();
            }
        }

        return ++count;
    }

    @Override
    public int getObjectCount() {

        return this.matrixEntries.size();
    }

    @Override
    public int getDistinctObjectCount() {

        Set<T> distinctObjects = new TreeSet<>();

        Set<Map.Entry<Point, T>> entrySet = this.matrixEntries.entrySet();
        for (Map.Entry<Point, T> entry : entrySet) {

            distinctObjects.add(entry.getValue());
        }

        return distinctObjects.size();
    }

    @Override
    public Iterator<T> iterator() {

        return new DepthFirstIterator();
    }

    @Override
    public T get(int row, int column) throws IllegalArgumentException {

        if (row < 0) throw new IllegalArgumentException("Row number cant be lower than 0!");
        if (column < 0) throw new IllegalArgumentException("Column number cant be lower than 0!");

        if (row > getRowCount() -1 ) throw new IllegalArgumentException("Row number is too large!");
        if (column > getColumnCount() -1) throw new IllegalArgumentException("Column number is too large!");

        return this.matrixEntries.getOrDefault(new Point(row, column), null);
    }

    @Override
    public T put(int row, int column, T value) throws IllegalArgumentException {

        if (row < 0) throw new IllegalArgumentException("Row number cant be lower than 0!");
        if (column < 0) throw new IllegalArgumentException("Column number cant be lower than 0!");

        Point p = new Point(row, column);

        if (this.matrixEntries.containsKey(p)) {

            T oldValue = this.matrixEntries.get(p);
            this.matrixEntries.remove(p);
            this.matrixEntries.put(p, value);

            return oldValue;

        } else {

            this.matrixEntries.put(p, value);
            return null;
        }
    }

    @Override
    public boolean contains(T value) {

        return this.matrixEntries.containsValue(value);
    }

    public class DepthFirstIterator implements Iterator<T> {

        List<T> valueList;
        int count;

        public DepthFirstIterator() {

            this.valueList = new ArrayList<>();

            for (int column = 0; column < getRowCount(); column++) {
                for (int row = 0; row < getColumnCount(); row++) {

                    if (get(row, column) != null) {

                        valueList.add(get(row, column));
                    }
                }
            }

            this.count = -1;
        }

        @Override
        public boolean hasNext() {

            return count + 1 < valueList.size();
        }

        @Override
        public T next() throws NoSuchElementException {

            if (!hasNext()) throw new NoSuchElementException("No more elements!");

            this.count++;

            return this.valueList.get(count);
        }
    }
}
