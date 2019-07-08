package com.project.PoketQue.Models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "Statistics")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "stats_Proc",
                procedureName = "stats_Proc",
                resultClasses = Statistics.class)
})
public class Statistics {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statsID;

    @Column(name="statsTime")
    @Version
    @Type(type = "dbtimestamp")
    private Date statsTime;
    
    //FORIEGN KEY branch_id coming from the branch table
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn( name = "fk_branch_id", foreignKey=@ForeignKey(name="branchID"))
    private Branch branch;

    //Default constructor
    public Statistics() {}

    //Parameterized Constructor
    public Statistics(Branch br) {
    	this.branch = br;
    }
    
    //Getters and Setters of properties
   /**
    * @return the statsID
    */
   public Integer getStatsID() {return statsID;}
   /**
    * @return the statsTime
    */
   public Date getStatsTime() {return statsTime;}
   /**
    * @return the Branch object related to the stats foreign key
    */
   public Branch getBranch() {return branch;}

   /**
    * @param statsID the statsID to set
    */
   public void setStatsID(Integer statsID) {this.statsID = statsID;}
   /**
    * @param statsTime the statsTime to set
    */
   public void setStatsTime(Date statsTime) {this.statsTime = statsTime;}
   public void setBranch(Branch branch) {this.branch = branch;}
}