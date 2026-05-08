package ex1;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccessLog {
    private LocalDateTime timestamp;
    private String userId;
    private String endpoint;
    private int responseTimeMs;
    private int statusCode;

    public AccessLog(String logLine) {
        String[] logData = logLine.split(";");
        LocalDateTime timestamp = LocalDateTime.parse(logData[0]);
        String userId = logData[1];
        String endpoint = logData[2];
        int responseTimeMs = Integer.parseInt(logData[3]);
        int statusCode = Integer.parseInt(logData[4]);

        this(timestamp, userId, endpoint, responseTimeMs, statusCode);
    }

    public AccessLog(LocalDateTime timestamp, String userId, String endpoint, int responseTimeMs, int statusCode) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.endpoint = endpoint;
        this.responseTimeMs = responseTimeMs;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public int getResponseTimeMs() {
        return responseTimeMs;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", responseTimeMs=" + responseTimeMs +
                ", statusCode=" + statusCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLog accessLog = (AccessLog) o;
        return responseTimeMs == accessLog.responseTimeMs && statusCode == accessLog.statusCode && Objects.equals(timestamp, accessLog.timestamp) && Objects.equals(userId, accessLog.userId) && Objects.equals(endpoint, accessLog.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, userId, endpoint, responseTimeMs, statusCode);
    }

    public static List<AccessLog> readFromFile(String filename) {
        List<AccessLog> logs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(new AccessLog(line));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
