package com.project.PoketQue.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "QueModel")
public class QueModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name="QuePostion", nullable=false)
        private Integer QuePostion;

        @Column(name="AppointmentTime", nullable=false)
        private LocalDateTime AppointmentTime;

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
        @JoinColumn( name = "fk_branch_id",foreignKey=@ForeignKey(name="branchid"))
        private Branch branch;

        public QueModel() {}

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Integer getQuePostion() {
            return QuePostion;
        }
        public void setQuePostion(Integer quePostion) {
            QuePostion = quePostion;
        }
        public LocalDateTime getAppointmentTime() {
            return AppointmentTime;
        }
        public void setAppointmentTime(LocalDateTime appointmentTime) {
            AppointmentTime = appointmentTime;
        }
        public Branch getBranch() {
            return branch;
        }
        public void setBranch(Branch branch) {
            this.branch = branch;
        }
    }

