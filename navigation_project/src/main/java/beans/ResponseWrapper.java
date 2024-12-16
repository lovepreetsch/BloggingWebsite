package beans;
public class ResponseWrapper<T> {
    private int success;
    private int status;
    private String message;
    private T data;

    public ResponseWrapper(int success, T data, int status, String message) {
        this.success = success;
        this.data = data;
        this.status = status;
        this.message = message;
    }
    
    public ResponseWrapper(int success, int status, String message) {
		super();
		this.success = success;
		this.status = status;
		this.message = message;
	}
    
	public ResponseWrapper() {
		super();
	}

	// Getters and setters
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

//	@Override
//	public String toString() {
//		return "ResponseWrapper [success=" + success + ", status=" + status + ", message=" + message + ", data=" + data
//				+ "]";
//	}
	
	@Override
	public String toString() {
	    StringBuilder dataRepresentation = new StringBuilder();
	    
	    if (data != null) {
	        // If the data type overrides toString, it will display its content
	        dataRepresentation.append(data.toString());
	        
	        // For arrays
	        if (data.getClass().isArray()) {
	            dataRepresentation = new StringBuilder(java.util.Arrays.deepToString((Object[]) data));
	        }
	        // For collections
	        else if (data instanceof java.util.Collection<?>) {
	            dataRepresentation = new StringBuilder(data.toString());
	        }
	        // For maps
	        else if (data instanceof java.util.Map<?, ?>) {
	            dataRepresentation = new StringBuilder(data.toString());
	        }
	    } else {
	        dataRepresentation.append("null");
	    }

	    return "ResponseWrapper [success=" + success + ", status=" + status + ", message=" + message + ", data=" + dataRepresentation + "]";
	}

    
}
