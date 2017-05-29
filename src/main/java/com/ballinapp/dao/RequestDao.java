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
                + "request.city, request.date, request.time, request.address, request.sent_at "
                + "FROM team INNER JOIN request ON team.team_id = request.sender_team_id WHERE receiver_team_id = ?;";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> rows = query.setLong(0, id).list();
        rows.forEach((row) -> {
            newRequests.add(new NewRequest(row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString(),
                    row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString(), row[8].toString()));
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
}
