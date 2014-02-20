public class RingBuffer {

	int first, last, capacity;
	double[] buffer;

	RingBuffer(int capacity) {			// create an empty ring buffer, with given max capacity
		buffer = new double[capacity];
		first = 0;
		last = 0;
		this.capacity = capacity;
	}

    int size() { 						// return number of items currently in the buffer
		int sum = 0;
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] != 0)
				sum++;
		}  
		return sum;
    }

	boolean isEmpty() {					// is the buffer empty (size equals zero)?
		if (size() == 0)
			return true;
		else 
			return false;
	}

	boolean isFull() {					// is the buffer full  (size equals capacity)?
		if (size() == capacity)
			return true;
		else
			return false;
	}

 	void enqueue(double x) {			// add item x to the end
 		buffer[last] = x;
 		last++;
 		if (last == capacity)
 			last = 0;
 	}

 	double dequeue() {					// delete and return item from the front
 		double temp = buffer[first];
 		buffer[first] = 0;
 		first++;
 		if (first == capacity)
 			first = 0;
 		return temp;
 	}

 	double peek() {						// return (but do not delete) item from the front
 		//if (buffer[first] == 0)
 		//	throw new RuntimeException();
 		return buffer[first];
 	}

 	public static void main(String[] arguments) {	// test client, tests all methods
 	  int N = Integer.parseInt(arguments[0]);
      RingBuffer buffer = new RingBuffer(N);  
      for (int i = 1; i <= N; i++) {
          buffer.enqueue(i);
      }
      double t = buffer.dequeue();
      buffer.enqueue(t);
      System.out.println("Size after wrap-around is " + buffer.size());
      while (buffer.size() >= 2) {
          double x = buffer.dequeue();
          double y = buffer.dequeue();
          buffer.enqueue(x + y);
      }
      System.out.println(buffer.peek());
 	}
 }