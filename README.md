# Serialzer
A small library to (De)serialize a class into Java.

Usage:

# Define the model

  public class MyData implements Serializable {
      private int data;
      
      public MyData() {} // Must have a parameterless default constructor
      
      public MyData(int _data) {
          data = _data;
      }
      
      @Override
      public void deserialize(DataReader reader) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
          data = reader.readInt();
      }
      
      @Override
      public void serialize(DataWriter writer) {
          writer.append(baseData);
      }
  }
  
# Serialize
  
  MyData myData = new MyData(15);
  byte[] bytes = Serializer.serialize(myData);

# Deserialize

  MyData myData = Serializer.deserialize(bytes, MyData.class);
  
# Current limitations

1. Cyclic references are not supported
2. Non-primitive array types cannot be directly serialized
  
  // Example of Non-primitive array serialization
  // Inside serialize method of a serializable class
  // array is a non-primitive array type
  
  writer.append(array.length);
  for(int i=0; i<array.length; i++) writer.append(array[i]);
  
  // De-serialization
  
  int l = reader.readInt();
  array = new MyData[l];
  for(int i=0; i<l; i++) array[i] = reader.read(MyData.class);
  
3. Super class type fields holding sub-class type data will be deserialized based on the sub-class. This might lead to errors. Remember to add extra logic in serialize method to recognize the actual data type being written. While deserializing, use the same logic to determine the actual type rather than deserializing in super type.
