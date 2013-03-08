package org.kairosdb.datastore.h2.orm;

import java.util.*;
import genorm.runtime.*;

/**
	This class has been automatically generated by GenORMous.  This file
	should not be modified.
	
*/
public class Metric_base extends GenOrmRecord
	{
	protected static final Logger s_logger = LoggerFactory.getLogger(Metric.class.getName());

	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";

	//Change this value to true to turn on warning messages
	private static final boolean WARNINGS = false;
	private static final String SELECT = "SELECT this.\"id\", this.\"name\" ";
	private static final String FROM = "FROM metric this ";
	private static final String WHERE = "WHERE ";
	private static final String KEY_WHERE = "WHERE \"id\" = ?";
	
	public static final String TABLE_NAME = "metric";
	public static final int NUMBER_OF_COLUMNS = 2;
	
	
	private static final String s_fieldEscapeString = "\""; 
	
	public static final GenOrmFieldMeta ID_FIELD_META = new GenOrmFieldMeta("id", "string", 0, true, false);
	public static final GenOrmFieldMeta NAME_FIELD_META = new GenOrmFieldMeta("name", "string", 1, false, false);

	
		
	//===========================================================================
	public static MetricFactoryImpl factory = new MetricFactoryImpl();
	
	public static interface MetricFactory extends GenOrmRecordFactory
		{
		public boolean delete(String id);
		public Metric find(String id);
		public Metric findOrCreate(String id);
		}
	
	public static class MetricFactoryImpl //Inherit interfaces
			implements MetricFactory 
		{
		public static final String CREATE_SQL = "CREATE CACHED TABLE metric (\n	\"id\" VARCHAR  NOT NULL,\n	\"name\" VARCHAR  NULL,\n	PRIMARY KEY (\"id\")\n	)";

		private ArrayList<GenOrmFieldMeta> m_fieldMeta;
		private ArrayList<GenOrmConstraint> m_foreignKeyConstraints;
		
		protected MetricFactoryImpl()
			{
			m_fieldMeta = new ArrayList<GenOrmFieldMeta>();
			m_fieldMeta.add(ID_FIELD_META);
			m_fieldMeta.add(NAME_FIELD_META);

			m_foreignKeyConstraints = new ArrayList<GenOrmConstraint>();
			}
			
		protected Metric newMetric(java.sql.ResultSet rs)
			{
			Metric rec = new Metric();
			((Metric_base)rec).initialize(rs);
			return ((Metric)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
	
		//---------------------------------------------------------------------------
		/**
			Returns a list of the feild meta for the class that this is a factory of
		*/
		public List<GenOrmFieldMeta> getFields()
			{
			return (m_fieldMeta);
			}

		//---------------------------------------------------------------------------
		/**
			Returns a list of foreign key constraints
		*/
		public List<GenOrmConstraint> getForeignKeyConstraints()
			{
			return (m_foreignKeyConstraints);
			}
			
		//---------------------------------------------------------------------------
		/**
			Returns the SQL create statement for this table
		*/
		public String getCreateStatement()
			{
			return (CREATE_SQL);
			}
			
		//---------------------------------------------------------------------------
		/**
			Creates a new entry with the specified primary keys.
		*/
		public Metric create(String id)
			{
			Metric rec = new Metric();
			rec.m_isNewRecord = true;
			
			((Metric_base)rec).setId(id);

			
			return ((Metric)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
		//---------------------------------------------------------------------------
		/**
			Creates a new entry that is empty
		*/
		public Metric createRecord()
			{
			Metric rec = new Metric();
			rec.m_isNewRecord = true;
			
			return ((Metric)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
			
		//---------------------------------------------------------------------------
		/**
		If the table has a primary key that has a key generator this method will 
		return a new table entry with a generated primary key.
		@return GSMetric with generated primary key
		*/
		public Metric createWithGeneratedKey()
			{
			Metric rec = new Metric();
			
			rec.m_isNewRecord = true;
			
			GenOrmKeyGenerator keyGen = GenOrmDataSource.getKeyGenerator("metric");
			if (keyGen != null)
				{
				rec.setId(
						(String)keyGen.generateKey());
				}
			
			return ((Metric)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
			
		//---------------------------------------------------------------------------
		/**
		A generic api for finding a record.
		@param keys This must match the primary key for this record.  If the 
		record has multiple primary keys this parameter must be of type Object[] 
		where each element is the corresponding key.
		@return GSMetric or null if no record is found
		*/
		public Metric findRecord(Object keys)
			{
			return (find((String)keys));
			}
			
		//---------------------------------------------------------------------------
		/**
			Deletes the record with the specified primary keys.
			The point of this api is to prevent a hit on the db to see if the record
			is there.  This call will add a record to the next transaction that is 
			marked for delete. 
			
			@return Returns true if the record was previous created and existed
			either in the transaction cache or the db.
		*/
		public boolean delete(String id)
			{
			boolean ret = false;
			Metric rec = new Metric();
			
			((Metric_base)rec).initialize(id);
			GenOrmConnection con = GenOrmDataSource.getGenOrmConnection();
			Metric cachedRec = (Metric)con.getCachedRecord(rec.getRecordKey());
			
			if (cachedRec != null)
				{
				ret = true;
				cachedRec.delete();
				}
			else
				{
				rec = (Metric)con.getUniqueRecord(rec);  //This adds the record to the cache
				rec.delete();
				ret = rec.flush();
				rec.setIgnored(true); //So the system does not try to delete it again at commmit
				}
				
			return (ret);
			}
			
		//---------------------------------------------------------------------------
		/**
		Find the record with the specified primary keys
		@return GSMetric or null if no record is found
		*/
		public Metric find(String id)
			{
			Metric rec = new Metric();
			
			//Create temp object and look in cache for it
			((Metric_base)rec).initialize(id);
			rec = (Metric)GenOrmDataSource.getGenOrmConnection().getCachedRecord(rec.getRecordKey());
			
			java.sql.PreparedStatement genorm_statement = null;
			java.sql.ResultSet genorm_rs = null;
			
			if (rec == null)
				{
				try
					{
					//No cached object so look in db
					genorm_statement = GenOrmDataSource.prepareStatement(SELECT+FROM+KEY_WHERE);
					genorm_statement.setString(1, id);

					s_logger.debug(genorm_statement.toString());
						
					genorm_rs = genorm_statement.executeQuery();
					if (genorm_rs.next())
						rec = newMetric(genorm_rs);
					}
				catch (java.sql.SQLException sqle)
					{
					throw new GenOrmException(sqle);
					}
				finally
					{
					try
						{
						if (genorm_rs != null)
							genorm_rs.close();
							
						if (genorm_statement != null)
							genorm_statement.close();
						}
					catch (java.sql.SQLException sqle2)
						{
						throw new GenOrmException(sqle2);
						}
					}
				}
				
			return (rec);
			}
		
		//---------------------------------------------------------------------------
		/**
		This is the same as find except if the record returned is null a new one 
		is created with the specified primary keys
		@return A new or existing record.  
		*/
		public Metric findOrCreate(String id)
			{
			Metric rec = find(id);
			if (rec == null)
				rec = create(id);
				
			return (rec);
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
		*/
		public ResultSet select(String where)
			{
			return (select(where, null));
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
			@param orderBy sql order by statement
		*/
		public ResultSet select(String where, String orderBy)
			{
			ResultSet rs = null;
			java.sql.Statement stmnt = null;
			
			try
				{
				stmnt = GenOrmDataSource.createStatement();
				StringBuilder sb = new StringBuilder();
				sb.append(SELECT);
				sb.append(FROM);
				if (where != null)
					{
					sb.append(WHERE);
					sb.append(where);
					}
					
				if (orderBy != null)
					{
					sb.append(" ");
					sb.append(orderBy);
					}
				
				String query = sb.toString();
				rs = new SQLResultSet(stmnt.executeQuery(query), query, stmnt);
				}
			catch (java.sql.SQLException sqle)
				{
				try
					{
					if (stmnt != null)
						stmnt.close();
					}
				catch (java.sql.SQLException sqle2) { }
					
				throw new GenOrmException(sqle);
				}
				
			return (rs);
			}
			
		
		//---------------------------------------------------------------------------
		/**
			Calls all query methods with test parameters.
		*/
		public void testQueryMethods()
			{
			ResultSet rs;
			}
		}
		
	//===========================================================================
	public static interface ResultSet extends GenOrmResultSet
		{
		public ArrayList<Metric> getArrayList(int maxRows);
		public ArrayList<Metric> getArrayList();
		public Metric getRecord();
		public Metric getOnlyRecord();
		}
		
	//===========================================================================
	private static class SQLResultSet 
			implements ResultSet
		{
		private java.sql.ResultSet m_resultSet;
		private java.sql.Statement m_statement;
		private String m_query;
		private boolean m_onFirstResult;
		
		//------------------------------------------------------------------------
		protected SQLResultSet(java.sql.ResultSet resultSet, String query, java.sql.Statement statement)
			{
			m_resultSet = resultSet;
			m_statement = statement;
			m_query = query;
			m_onFirstResult = false;
			}
		
		//------------------------------------------------------------------------
		/**
			Closes any underlying java.sql.Result set and java.sql.Statement 
			that was used to create this results set.
		*/
		public void close()
			{
			try
				{
				m_resultSet.close();
				m_statement.close();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
			@param maxRows if the result set contains more than this param
				then an exception is thrown
		*/
		public ArrayList<Metric> getArrayList(int maxRows)
			{
			ArrayList<Metric> results = new ArrayList<Metric>();
			int count = 0;
			
			try
				{
				if (m_onFirstResult)
					{
					count ++;
					results.add(factory.newMetric(m_resultSet));
					}
					
				while (m_resultSet.next() && (count < maxRows))
					{
					count ++;
					results.add(factory.newMetric(m_resultSet));
					}
					
				if (m_resultSet.next())
					throw new GenOrmException("Bound of "+maxRows+" is too small for query ["+m_query+"]");
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
		
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
		*/
		public ArrayList<Metric> getArrayList()
			{
			ArrayList<Metric> results = new ArrayList<Metric>();
			
			try
				{
				if (m_onFirstResult)
					results.add(factory.newMetric(m_resultSet));
					
				while (m_resultSet.next())
					results.add(factory.newMetric(m_resultSet));
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the underlying java.sql.ResultSet object
		*/
		public java.sql.ResultSet getResultSet()
			{
			return (m_resultSet);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the current record in the result set
		*/
		public Metric getRecord()
			{
			return (factory.newMetric(m_resultSet));
			}
			
		//------------------------------------------------------------------------
		/**
			This call expects only one record in the result set.  If multiple records
			are found an excpetion is thrown.
			The ResultSet object is automatically closed by this call.
		*/
		public Metric getOnlyRecord()
			{
			Metric ret = null;
			
			try
				{
				if (m_resultSet.next())
					ret = factory.newMetric(m_resultSet);
					
				if (m_resultSet.next())
					throw new GenOrmException("Multiple rows returned in call from GSMetric.getOnlyRecord");
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
				
			close();
			return (ret);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns true if there is another record in the result set.
		*/
		public boolean next()
			{
			boolean ret = false;
			m_onFirstResult = true;
			try
				{
				ret = m_resultSet.next();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			
			return (ret);
			}
		}
		
	//===========================================================================
		
	private GenOrmString m_id;
	private GenOrmString m_name;

	
	private List<GenOrmRecordKey> m_foreignKeys;
	
	public List<GenOrmRecordKey> getForeignKeys() { return (m_foreignKeys); }


	//---------------------------------------------------------------------------
	/**
		id is the combination of the name and tags
	*/
	public String getId() { return (m_id.getValue()); }
	public Metric setId(String data)
		{
		boolean changed = m_id.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(ID_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_id.setPrevValue(data);
			}
			
		return ((Metric)this);
		}
		

	//---------------------------------------------------------------------------
	/**
	*/
	public String getName() { return (m_name.getValue()); }
	public Metric setName(String data)
		{
		boolean changed = m_name.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(NAME_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_name.setPrevValue(data);
			}
			
		return ((Metric)this);
		}
		
	public boolean isNameNull()
		{
		return (m_name.isNull());
		}
		
	public Metric setNameNull()
		{
		boolean changed = m_name.setNull();
		
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(NAME_FIELD_META.getDirtyFlag());
			}
		
		return ((Metric)this);
		}
	
	
	
	
	//---------------------------------------------------------------------------
	protected void initialize(String id)
		{
		m_id.setValue(id);
		m_id.setPrevValue(id);

		}
		
	//---------------------------------------------------------------------------
	protected void initialize(java.sql.ResultSet rs)
		{
		try
			{
			if (s_logger.isDebug())
				{
				java.sql.ResultSetMetaData meta = rs.getMetaData();
				for (int I = 1; I <= meta.getColumnCount(); I++)
					{
					s_logger.debug("Reading - "+meta.getColumnName(I) +" : "+rs.getString(I));
					}
				}
			m_id.setValue(rs, 1);
			m_name.setValue(rs, 2);

			}
		catch (java.sql.SQLException sqle)
			{
			throw new GenOrmException(sqle);
			}
		}
	
	//---------------------------------------------------------------------------
	/*package*/ Metric_base()
		{
		super(TABLE_NAME);
		m_logger = s_logger;
		m_foreignKeys = new ArrayList<GenOrmRecordKey>();
		m_dirtyFlags = new java.util.BitSet(NUMBER_OF_COLUMNS);
		

		m_id = new GenOrmString(ID_FIELD_META);
		addField(m_id);

		m_name = new GenOrmString(NAME_FIELD_META);
		addField(m_name);

		GenOrmRecordKey foreignKey;
		}
	
	//---------------------------------------------------------------------------
	@Override
	public GenOrmConnection getGenOrmConnection()
		{
		return (GenOrmDataSource.getGenOrmConnection());
		}
		
	//---------------------------------------------------------------------------
	@Override
	public String getFieldEscapeString()
		{
		return (s_fieldEscapeString);
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setMTS()
		{
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setCTS()
		{
		}
		
	//---------------------------------------------------------------------------
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		
		sb.append("id=\"");
		sb.append(m_id.getValue());
		sb.append("\" ");
		sb.append("name=\"");
		sb.append(m_name.getValue());
		sb.append("\" ");

		
		return (sb.toString().trim());
		}
		
	//===========================================================================

	
	
	}
	
	