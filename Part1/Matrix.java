class Matrix extends Sequence {
  int row, col;
  Sequence matrix;
  Sequence rows, columns;
  
  public Matrix(int rowsize, int colsize) {
    row = rowsize;
    col = colsize;
    matrix = new Sequence();

    for(int i = 0; i < row*col; i++) { //1D matrix functioning as 2D matrix
      Element item = new MyInteger(0);
      matrix.add(item, i);
    }
  }
  
  public void Print() {
    Sequence temp = matrix;
    for(int i = 0; i < row; i++) {
      System.out.print("[ ");
      for(int j = 0; j < col; j++){
        System.out.print(((MyInteger)(temp.Get())).Get());
        System.out.print(" ");
        temp = temp.next;
      }
      System.out.println("]");
    }
  }
  
  private int getIndex(int rowsize, int colsize) { //convert 2D index to 1D index
    return ((rowsize)*(col) + (colsize + 1));
  }
  
  public void Set(int rowsize, int colsize, int value) {
    int index = getIndex(rowsize, colsize);
    Sequence temp = matrix;
    for(int i = 0; i < index - 1; i++){ 
      temp = temp.next;
    }
    temp.Set(new MyInteger(value));
  }
 
  public void Set(int index, int value) {
  	Sequence temp = matrix;
  	for(int i = 0; i < index; i++){ 
  		temp = temp.next;
  	}
  	temp.Set(new MyInteger(value));
  }  
  
  public int Get(int rowsize, int colsize) {
    int index = getIndex(rowsize, colsize);
    Sequence temp = matrix;
    for(int i = 0; i < index - 1; i++){ 
      temp = temp.next;
    }    
    int result = ((MyInteger)(temp.Get())).Get();
    return result;
  }
  
  public int Get(int index) {
    Sequence temp = matrix;
    for(int i = 0; i < index; i++){ 
      temp = temp.next;
    }    
    int result = ((MyInteger)(temp.Get())).Get();
    return result;
  }  
  
  public Matrix Sum(Matrix mat) {
  	if(row != mat.row || col != mat.col) {
  		System.out.println("Matrix dimensions incompatible for Sum");
  		System.exit(1);
  	}
   	Matrix sum = new Matrix(row, col);
   	int m1, m2;
   	for(int i = 0; i < row*col; i++) {
   		m1 = this.Get(i);
   		m2 = mat.Get(i);
   		sum.Set(i, m1 + m2);
    }
    return sum;
  }
  
  public Matrix Product(Matrix mat) { //this * mat -> m1.product(m2)
  	if(mat.row != this.col) {
		  System.out.println("Matrix dimensions incompatible for Product");
		  System.exit(1);
  	}
  	int SOP = 0;
  	Matrix product = new Matrix(this.row, mat.col);
  	for(int i = 0; i < this.row; i++) {
  		for(int j = 0; j < mat.col; j++) {
  			for(int k = 0; k < this.col; k++) {
  				int m1 = this.Get(i, k);
  				int m2 = mat.Get(k, j);
  				SOP += (m1 * m2); 
  			}
  			product.Set(i, j, SOP);
  			SOP = 0;
  		}
  	}
  	return product;  
  }
}