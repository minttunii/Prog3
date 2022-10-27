
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class NdArray<E> extends AbstractCollection<E>{
    private final int N;
    private int[] dimensions;
    private int size;
    private Object[] NdArray;
    
    public NdArray(Integer firstDimLen, Integer ...furtherDimLens){
       this.N = 1 + furtherDimLens.length;
       
       // Dimensions must be non-negative
       if(firstDimLen < 0){
           throw new NegativeArraySizeException(String.format("Illegal dimension"
                   + " size %d.", firstDimLen));
       }
       for(int i = 0; i < N-1; i++){
           if(furtherDimLens[i] < 0){
               throw new NegativeArraySizeException(String.format("Illegal dimension"
                   + " size %d.", furtherDimLens[i]));
           }
       }    
       
        int[] dimsizes = new int[N];
        dimsizes[0] = firstDimLen;
        
        int s = firstDimLen;
        int i = 1;
        for(Integer dim : furtherDimLens){
            dimsizes[i] = dim; 
            s *= dim;
            i += 1;
        }
        this.dimensions = dimsizes;
        this.size = s;
        this.NdArray = new Object[size];
    }
    
    private int getOffset(int[] indices){
        int offset = 0;
        int prod ;
        int prod2 = 0;
        
        for(int i = 0; i < N; i++){
            prod = 1;
            for(int j = i + 1; j < N; j++){
                prod *= dimensions[j];
            }
            prod2 += prod * indices[i];
        }
        offset += prod2;     
        return offset;
    }
    
    private void isLegal(int[] indices){
        // Dimensions must be same as the arrays dimension
        if(indices.length != N){
            throw new IllegalArgumentException(String.format("The array has %d"
                    + " dimensions but %d indices were given.", N, indices.length));
        }
        // All indexes must be legal 
        int i = 0;
        for(Integer dim : dimensions){
            if(indices[i] < 0 || indices[i] >= dim){
            throw new IndexOutOfBoundsException(String.format("Illegal index %d"
                    + " for dimension %d of length %d.", i, i +1, indices[i] ));
        }
            i += 1;
        }

    }
    
    @SuppressWarnings("unchecked")    
    public E get(int... indices){
        //Check if indices are legal or not
        isLegal(indices);
        
        int offset = getOffset(indices);
        return (E) NdArray[offset];
      
    }
    
    @SuppressWarnings("unchecked")
    public void set(E item, int... indices){
        //Check if indices are legal or not
        isLegal(indices);
        
        int offset = getOffset(indices);
        NdArray[offset] = item;
    }
    
    public int[] getDimensions(){
        int[] dimsizes = new int[N]; 
        int i = 0;
        for(Integer dim : dimensions){
            dimsizes[i] = dim;
            i += 1;
        }
        return dimsizes;
    }
    
     @Override
    public int size() {
        return size;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new NdArrayIterator();
    }
    
    private class NdArrayIterator implements Iterator<E> {
        int pos = 0;
     
        @Override
        public boolean hasNext() {
            return pos < size;            
        }
        
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if(pos >= size) {
               throw new NoSuchElementException(String.format("No more values in the array!"));
            }
            return (E) NdArray[pos++];
        }
        
    }
 
}
