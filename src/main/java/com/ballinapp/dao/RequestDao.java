package com.ballinapp.dao;

import com.ballinapp.dao.base_manager.Manager;
import com.ballinapp.data.info.NewRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.model.Request;

@Repository
public class RequestDao extends Manager {
    
    private static final RequestDao instance = new RequestDao();
    
    private RequestDao() {
    }
    
    public static RequestDao getInstance() {
        return instance;
    }

    public void sendRequest(Request request) {
        getCurrentSession().save(request);
    }

    public Request getRequestById(int id) {
        return (Request) getCurrentSession().get(Request.class, id);
    }

    public List<NewRequest> getRequests(int id) {
        List<NewRequest> newRequests = new ArrayList<>();
        String sql = "SELECT team.name, request.message, request.contact, request.state, "
                + "request.city, request.date, request.time, request.address, request.sent_at, request.id, request.status "
                + "FROM team INNER JOIN request ON team.id = request.sender_team_id WHERE receiver_team_id = ?;";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.setInteger(0, id).list();
        rows.forEach((row) -> newRequests.add(new NewRequest(row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString(),
                row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString(), row[8].toString(),
                Integer.parseInt(row[9].toString()), Boolean.parseBoolean(row[10].toString()))));

        return newRequests;
    }

    public void deleteAllRequests(int teamId) {
        String hql = "DELETE FROM Request WHERE receiverTeamId.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", teamId);
        query.executeUpdate();
    }
    
    public void requestResponse(int requestId, boolean response) {
        String hql = "UPDATE Request SET status = :response WHERE id = :requestId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("response", response);
        query.setParameter("requestId", requestId);
        query.executeUpdate();
    }
    
    public void deleteRequest(int requestId) {
        String hql = "DELETE FROM Request WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", requestId);
        query.executeUpdate();
    }

    public List<NewRequest> getSentRequests(int teamId) {
        List<NewRequest> requests = new ArrayList<>();
        String sql = "SELECT request.message, (SELECT name FROM team WHERE team.id = receiver_team_id) as opponent, "
                + "request.status, request.contact, request.state, request.city, request.address, request.date, request.time, "
                + "request.sent_at, request.id FROM request INNER JOIN team ON request.sender_team_id = team.id WHERE sender_team_id = ?;";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.setInteger(0, teamId).list();
        rows.forEach((row) -> requests.add(new NewRequest(row[1].toString(), row[0].toString(), row[3].toString(), row[4].toString(),
                row[5].toString(), row[7].toString(), row[8].toString(), row[6].toString(),
                row[9].toString(), Integer.parseInt(row[10].toString()) ,Boolean.parseBoolean(row[2].toString()))));
        return requests;
    }

    public void removeFromMyRequests(int requestId) {
        String hql = "UPDATE Request SET receiverTeamId = 0 WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", requestId);
        query.executeUpdate();
    }
}
