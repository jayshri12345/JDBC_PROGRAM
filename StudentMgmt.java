import java.sql.*;
public class StudentMgmt {
	
		static Connection con=null;
		int affectedRow=0;
		private ResultSet res;
		
		StudentMgmt() throws SQLException
		{
			String url="jdbc:mysql://localhost:3306/info";
			String uname="root";
			String pass="root";
			con=DriverManager.getConnection(url,uname,pass);
			
		}
		public int saveStudent(Student std) throws SQLException
		{
			PreparedStatement st=con.prepareStatement("insert into Students (id, firstname, lastname) values (?,?,?)");
			st.setInt(1,std.getId());
			st.setString(2, std.getFirstname());
			st.setString(3, std.getLastname());
			affectedRow=st.executeUpdate();
			st.close();
			
			
			return affectedRow;
		}
		
		public Student getStudent(int id) throws SQLException
		{
			PreparedStatement st=con.prepareStatement("select * from Students where id="+id);
			res=st.executeQuery();
			while (res.next())
			{
			System.out.println(res.getInt("ID")+ " "+res.getString("Firstname")+ "  " +res.getString("Lastname"));
			}
			
			return null;
	}
		public int updateStudentFirstname(int id , String firstName)throws SQLException
		{
			
			PreparedStatement st=con.prepareStatement("update Students set firstname=? where  id="+id);
			st.setString(1, firstName);
			int affectedRow=st.executeUpdate();
			st.close();
			return affectedRow;
		}
		
		public Student getAllStudent() throws SQLException
		{
			PreparedStatement st=con.prepareStatement("select * from Students");
			res=st.executeQuery();
			while (res.next())
			{
			System.out.println(res.getInt("ID")+ " "+res.getString("Firstname")+ "  " +res.getString("Lastname"));
			}
		
			return null;
		}
		
		public int deleteStudent(int id) throws SQLException
		{
			
			PreparedStatement st=con.prepareStatement("delete from Students where id="+id);
			return st.executeUpdate();
		}
		
	public static void main(String[] args) throws SQLException
	{
		int count  = 101;
		Student s=new Student(count++, "dipali","patil");
		StudentMgmt obj=new StudentMgmt();
		
		/*int res=obj.saveStudent(s);
		if(res!=0)
			System.out.println("record Added");
		else
			System.out.println("record not added");
	*/
			obj.getStudent(1);
			obj.getAllStudent();
			obj.updateStudentFirstname( 2, "nayna");
			obj.getAllStudent();
			obj.deleteStudent(101);
			obj.getAllStudent();
			con.close();
				
	}
	

}
