package com.bastawesy.core.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CI_SYS_LOGS")
public class SystemLog extends BaseEntity {
    /*
     * Entity Fields
     */

    /**
     * Entity primary key ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private BigDecimal id;

    /**
     * Log message details. In case of errors, Exception.getStackTrace() will be used.
     */
    @Column(name = "DETAILS")
    private String details;

    /**
     * Message logging level.
     */
    @Column(name = "LOG_LEVEL", nullable = false, length = 10)
    private String logLevel;

    /**
     * Log message source class.
     */
    @Column(name = "LOG_SOURCE", nullable = false, length = 128)
    private String logSource;

    /**
     * Log message.
     */
    @Column(nullable = false)
    private String message;

    /**
     * Related business rule code if any.
     */
    @Column(name = "BRS_CODE", length = 16)
    private String businessRuleCode;

    /*
     * Constructors
     */

    /**
     * Construct new Log object. Initial value for logging level will be DEBUG.
     */
    public SystemLog() {
        super();
    }

    /**
     * Construct new Log object.
     *
     * @param msg         Log message.
     * @param logLevel       Log message level.
     * @param callerClass Caller class name.
     */
    public SystemLog(String msg, String logLevel, String callerClass) {
        setMessage(msg);
        this.logLevel=logLevel;
        setLogSource(callerClass);
    }

    /**
     * Get Log message details. In case of errors, Exception.getStackTrace() will be used.
     *
     * @return Log message details. In case of errors, Exception.getStackTrace() will be used.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set Log message details. In case of errors, Exception.getStackTrace() will be used.
     *
     * @param details Log message details. In case of errors, Exception.getStackTrace() will be used.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Get Entity primary key ID.
     *
     * @return Entity primary key ID.
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * Set Entity primary key ID.
     *
     * @param id Entity primary key ID.
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * Get Message logging level.
     *
     * @return Message logging level.
     */
    public String getLogLevel() {
        return logLevel;
    }


    /**
     * Get Log message source class
     *
     * @return Log message source class
     */
    public String getLogSource() {
        return logSource;
    }

    /**
     * Set Log message source class
     *
     * @param logSource Log message source class
     */
    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    /**
     * Get Log message.
     *
     * @return Log message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set Log message.
     *
     * @param message Log message.
     */
    public void setMessage(String message) {
        this.message = message;
    }



    /**
     * Set Related business rule code if any.
     *
     * @param businessRuleCode Related business rule code if any.
     */
    public void setBusinessRuleCode(String businessRuleCode) {
        this.businessRuleCode = businessRuleCode;
    }

    /**
     * Get Related business rule code if any.
     *
     * @return Related business rule code if any.
     */
    public String getBusinessRuleCode() {
        return businessRuleCode;
    }


}
