package com.ballinapp.dao;

import com.ballinapp.data.NewRequest;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.Request;
import com.ballinapp.data.Team;
import com.ballinapp.util.HibernateUtil;

@Repository
public class RequestDao {
    
    private static final RequestDao instance = new RequestDao();
    
    private RequestDao() {
    }
    
    public static RequestDao getInstance() {
        return instance;
    }

    private Session currentSession;
    private Transaction currentTransaction;
    
    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void sendRequest(Request request) {
        getCurrentSession().save(request);
    }

    public Request getRequestById(int id) {
        return (Request) getCurrentSession().get(Request.class, id);
    }

    public List<NewRequest> getRequests(Long id) {
        List<NewRequest> newRequests = new ArrayList<>();
        String sql = "SELECT team.name, request.message, request.contact, request.state, "
                + "request.city, request.date, request.time, request.address, request.sent_at, request.request_id, request.status "
                + "FROM team INNER JOIN request ON team.team_id = request.sender_team_id WHERE receiver_team_id = ?;";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.setLong(0, id).list();
        rows.forEach((row) -> {
            newRequests.add(new NewRequest(row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString(),
                    row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString(), row[8].toString(),
                    Integer.parseInt(row[9].toString()), Boolean.parseBoolean(row[10].toString())));
        });
        
        return newRequests;
    }

    public Team findTeamById(Long id) {
        return (Team) getCurrentSession().get(Team.class, id);
    }

    public void deleteAllRequests(Long teamId) {
        String sql = "DELETE FROM request WHERE receiver_team_id = ?";
        getCurrentSession().createSQLQuery(sql).setLong(0, teamId).executeUpdate();
    }
    
    public void requestResponse(int requestId, boolean response) {
        String sql = "UPDATE request SET status = ? WHERE request_id = ?";
        getCurrentSession().createSQLQuery(sql).setBoolean(0, response).setInteger(1, requestId).executeUpdate();
    }
    
    public void deleteRequest(int requestId) {
        String sql = "DELETE FROM request WHERE request_id = ?";
        getCurrentSession().createSQLQuery(sql).setInteger(0, requestId).executeUpdate();
    }
    
    public List<NewRequest> getSentRequests(Long teamId) {
        List<NewRequest> requests = new ArrayList<>();
        String sql = "SELECT request.message, (SELECT name FROM team WHERE team_id = receiver_team_id) as opponent, "
                + "request.status, request.contact, request.state, request.city, request.address, request.date, request.time, "
                + "request.sent_at, request.request_id FROM request INNER JOIN team ON request.sender_team_id = team.team_id WHERE sender_team_id = ?;";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.setLong(0, teamId).list();
        rows.forEach((row) -> {
            requests.add(new NewRequest(row[1].toString(), row[0].toString(), row[3].toString(), row[4].toString(),
                    row[5].toString(), row[7].toString(), row[8].toString(), row[6].toString(),
                    row[9].toString(), Integer.parseInt(row[10].toString()) ,Boolean.parseBoolean(row[2].toString())));
        });
        return requests;
    }

    public void removeFromMyRequests(int requestId) {
        String sql = "UPDATE request SET receiver_team_id = 0 WHERE request_id = ?";
        getCurrentSession().createSQLQuery(sql).setInteger(0, requestId).executeUpdate();
    }
    
    public boolean authenticate(String token, Long id) {
    	String sql = "SELECT * FROM team WHERE access_token = ? and team_id = ?";
    	SQLQuery query = getCurrentSession().createSQLQuery(sql);
    	int i = 0;
    	@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setString(0, token).setLong(1, id).list();
        for (@SuppressWarnings("unused") Object[] row : rows) {
        	i++;
        }
        
        if(i == 0) {
        	return false;
        }
    	
    	return true;
    }
}
