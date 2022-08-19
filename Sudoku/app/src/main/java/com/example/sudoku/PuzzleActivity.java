package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import static androidx.core.widget.TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration;
import static java.lang.System.exit;

public class PuzzleActivity extends AppCompatActivity {


    TextView[][] cellIn = new TextView[82][10];
    TextView[] cell = new TextView[82];
    ConstraintLayout[] cellBack = new ConstraintLayout[82];
    ConstraintLayout primary_tools,more_tools,pre_tools,keys;
    Button[] key = new Button[10];

    Chronometer timer;
    Button finish;
    ImageView lock_cross;
    ConstraintLayout validation,notes,lock,more_button,backspace,solve,hint,clear,pre_solve,pre_clear,pre_backspace,hint_2,mistakes,success,failure,back_to_home,back_to_home_2;
    TextView complete_msg,no_of_solutions,level,validation_status,notes_status,status_out,mistakes_no,total_time;
    ConstraintLayout finish_template;
    View dummy;

    Animation animFromTop,animToTop,animFadeIn;

    int[][] sq = new int[9][9];
    int[][] sa = new int[9][9];
    int[][] sw = new int[9][9];
    int[][] se = new int[9][9];
    int[][][] se3d = new int[9][9][9];
    int[][][] s3D = new int[9][9][9];
    int[][] s3D_l = new int[9][9];
    int current_cell=0, received_level, nob, lock_status=0, finalise_status=0, success_state=0;
    long received_time;
    boolean running;
    int no_mistakes;
    String game_mode;

        public void set_cell_text_2(int I, int pos) {
        int i,x,complete_status=1;
        for(i=1;i<=9;i++) cellIn[pos][i].setText("");
        cell[pos].setText(String.valueOf(I));
        
        if(sq[(pos-1)/9][(pos-1)%9] != 0) {
            cell[pos].setTextColor(this.getResources().getColor(R.color.black));
        }
        else {
            if( String.valueOf(validation_status.getText()).equals("ON") ) {
                if( I == sa[(pos-1)/9][(pos-1)%9] ) cell[pos].setTextColor(Color.parseColor("#43B524"));
                else cell[pos].setTextColor(Color.parseColor("#E82121"));
            }
            else {
                cell[pos].setTextColor(this.getResources().getColor(R.color.grey));
            }
        }

        for(i=1;i<=81;i++) {
            if(String.valueOf(cell[i].getText()).equals("")) x=0;
            else x=Integer.parseInt(String.valueOf(cell[i].getText()));
            if(x != sa[(i-1)/9][(i-1)%9]) complete_status=0;
        }
        if(complete_status == 1) {
            validation_status.setText("OFF");
            notes_status.setText("OFF");
            primary_tools.setVisibility(View.INVISIBLE);
            more_tools.setVisibility(View.INVISIBLE);
            keys.setVisibility(View.INVISIBLE);
            for(i=1;i<=81;i++) {
                cellBack[i].setBackgroundResource(R.drawable.background_2);
                if(sq[(i-1)/9][(i-1)%9] == 0) cell[i].setTextColor(this.getResources().getColor(R.color.blue));
            }
        }
    }
    public void print_a_2() {
        int i;
        current_cell=0;
        for(i=1;i<=81;i++) {
            if(sq[(i-1)/9][(i-1)%9] == 0) set_cell_text_2(sa[(i-1)/9][(i-1)%9], i);
            cellBack[i].setBackgroundResource(R.drawable.background_2);
        }
    }

    public void set_cell_text(int I, int pos) {
        int i,j,k,x,nos=0,con_q=0,complete_status=1;

        for(i=1;i<=9;i++) cellIn[pos][i].setText("");
        if(I!=0) cell[pos].setText(String.valueOf(I));
        else cell[pos].setText("");

        if(lock_status==0) {
            sw[(pos-1)/9][(pos-1)%9]=I;
            for(i=0;i<9;i++) {
                for(j=0;j<9;j++) {
                    s3D_l[i][j]=0;
                    for(k=0;k<9;k++) s3D[i][j][k]=0;
                    if(sw[i][j]!=0) if(con_q == 0) con_q=con(i,j);
                }
            }
            if( con_q == 0 ) {
                allp();
                nos = nos_limited(0,0,0);
            }
            if(nos == 0) {
                no_of_solutions.setText("No. of Solution : No Solution");
                lock_cross.setVisibility(View.VISIBLE);
                //lock.setEnabled(false);
            }
            else if(nos == 1) {
                no_of_solutions.setText("No. of Solution : One Solution");
                lock_cross.setVisibility(View.INVISIBLE);
                //lock.setEnabled(true);
            }
            else {
                no_of_solutions.setText("No. of Solution : More than One Solution");
                lock_cross.setVisibility(View.VISIBLE);
                //lock.setEnabled(false);
            }
        }
        if(sq[(pos-1)/9][(pos-1)%9] != 0) {
            cell[pos].setTextColor(this.getResources().getColor(R.color.black));
        }
        else {
            if( String.valueOf(validation_status.getText()).equals("ON") ) {
                if( I == sa[(pos-1)/9][(pos-1)%9] ) cell[pos].setTextColor(Color.parseColor("#43B524"));
                else {
                    cell[pos].setTextColor(Color.parseColor("#E82121"));
                    if( (I!=0) && (game_mode.equals("appa")) && (mistakes.getVisibility() == View.VISIBLE) ) {
                        int n = Integer.parseInt(String.valueOf(mistakes_no.getText()));
                        n++;
                        mistakes_no.setText(String.valueOf(n));
                        if(n>=3) {
                            finalise(dummy);
                        }
                    }
                }
            }
            else {
                cell[pos].setTextColor(this.getResources().getColor(R.color.grey));
            }
        }
        if(lock_status==2) {
            for(i=1;i<=81;i++) {
                if(String.valueOf(cell[i].getText()).equals("")) x=0;
                else x=Integer.parseInt(String.valueOf(cell[i].getText()));
                if(x != sa[(i-1)/9][(i-1)%9]) complete_status=0;
            }
            if(complete_status == 1) {
                success_state=1;
                validation_status.setText("OFF");
                notes_status.setText("OFF");
                primary_tools.setVisibility(View.INVISIBLE);
                more_tools.setVisibility(View.INVISIBLE);
                keys.setVisibility(View.INVISIBLE);
                complete_msg.setVisibility(View.VISIBLE);
                finish.setVisibility(View.VISIBLE);
                if(running) {
                    timer.stop();
                    running = false;
                }
                for(i=1;i<=81;i++) {
                    cellBack[i].setBackgroundResource(R.drawable.background_2);
                    if(sq[(i-1)/9][(i-1)%9] == 0) cell[i].setTextColor(this.getResources().getColor(R.color.blue));
                }
            }
        }
    }
    public void print_a() {
        int i;
        current_cell=0;
        for(i=1;i<=81;i++) {
            if(sq[(i-1)/9][(i-1)%9] == 0) set_cell_text(sa[(i-1)/9][(i-1)%9], i);
            cellBack[i].setBackgroundResource(R.drawable.background_2);
        }
    }
    public void print_q() {
        int i;
        //current_cell=0;
        for(i=1;i<=81;i++) {
            if(sq[(i-1)/9][(i-1)%9] != 0) {
                set_cell_text(sq[(i-1)/9][(i-1)%9], i);
                cellBack[i].setBackgroundResource(R.drawable.background_2);
            }
        }
    }

    public void allp() {
        int i,j,k,l;
        int[] n = new int[9];

        for(i=0;i<9;i++) {
            for(j=0;j<9;j++) {
                if(sw[i][j]==0) {
                    for(k=0;k<9;k++) n[k]=0;
                    for(k=0;k<9;k++) if(sw[i][k]!=0) n[sw[i][k]-1]++;
                    for(k=0;k<9;k++) if(sw[k][j]!=0) n[sw[k][j]-1]++;
                    for(k=((i/3)*3);k<(((i/3)*3)+3);k++)
                        for(l=((j/3)*3);l<(((j/3)*3)+3);l++)
                            if(sw[k][l]!=0) n[sw[k][l]-1]++;

                    for(k=0;k<9;k++) if(n[k]==0) s3D[i][j][s3D_l[i][j]++] = k+1;
                }
            }
        }
    }
    public int con(int a,int b) {
        int i,j;
        for(i=0;i<9;i++) if( (sw[a][i]==sw[a][b]) && (i!=b) ) return 1;
        for(i=0;i<9;i++) if( (sw[i][b]==sw[a][b]) && (i!=a) ) return 1;
        for(i=((a/3)*3);i<(((a/3)*3)+3);i++)
            for(j=((b/3)*3);j<(((b/3)*3)+3);j++)
                if( (sw[i][j]==sw[a][b]) && (i!=a) && (j!=b) ) return 1;
        return 0;
    }

    public int nos_limited(int i,int j,int nos_p) {
        if(i==9) return 1;

        int I,J,nos=0,nos_c=0,k;

        if(j==8) { I=i+1; J=0; }
        else { I=i; J=j+1; }

        if(sw[i][j]!=0) {
            nos_c=nos_limited(I,J,(nos+nos_p));
            nos=nos+nos_c;
        }
        else {
            for(k=0;k<s3D_l[i][j];k++) {
                sw[i][j]=s3D[i][j][k];
                if(con(i,j)!=0) sw[i][j]=0;
                else if( (nos_c=nos_limited(I,J,(nos+nos_p))) == 0 ) sw[i][j]=0;
                else {
                    nos=nos+nos_c;
                    if((nos+nos_p)>=2) break;
                }
            }
            sw[i][j]=0;
        }
        return nos;
    }
    public int nos_unlimited(int i,int j) {
        if(i==9) return 1;

        int I,J,nos=0,nos_c=0,k;

        if(j==8) { I=i+1; J=0; }
        else { I=i; J=j+1; }

        if(sw[i][j]!=0) {
            nos_c=nos_unlimited(I,J);
            nos=nos+nos_c;
        }
        else {
            for(k=0;k<s3D_l[i][j];k++) {
                sw[i][j]=s3D[i][j][k];
                if(con(i,j)!=0) sw[i][j]=0;
                else if( (nos_c=nos_unlimited(I,J)) == 0 ) sw[i][j]=0;
                else nos=nos+nos_c;
            }
            sw[i][j]=0;
        }
        return nos;
    }

    public int get_answer(int i,int j) {
        if(i==9) return 0;

        int I,J,k;

        if(j==8) { I=i+1; J=0; }
        else { I=i; J=j+1; }

        if(sw[i][j]!=0) {
            if (get_answer(I, J) != 0) return 1;
        }
        else {
            for(k=0;k<s3D_l[i][j];k++) {
                sw[i][j]=s3D[i][j][k];
                if(con(i,j)!=0) sw[i][j]=0;
                else if(get_answer(I,J)!=0) sw[i][j]=0;
                else break;
            }
            if(sw[i][j]==0) return 1;
        }
        return 0;
    }

    public int generate_solved_puzzle(int i,int j) {
        if(i==9) return 0;

        int I,J,k,l,m;

        for(k=0;k<9;k++) s3D[i][j][k]=k+1;

        if(j==8) { I=i+1; J=0; }
        else { I=i; J=j+1; }

        if(sw[i][j]!=0) { if(generate_solved_puzzle(I,J)!=0) return 1; }
        else {
            for(k=9;k>0;k--) {
                l = (int) Math.floor(Math.random()*k);
                for(m=0;m<=l;m++) if(s3D[i][j][m]==-1) l++;

                sw[i][j]=s3D[i][j][l];
                s3D[i][j][l]=-1;

                if(con(i,j)!=0) sw[i][j]=0;
                else if(generate_solved_puzzle(I,J)!=0) sw[i][j]=0;
                else break;
            }
            if(sw[i][j]==0) return 1;
        }
        return 0;
    }
    public int remove_numbers(int[] N, int N_l, int x) {
        if(x==nob) return 1;

        int I,J,nos,i,j,k,l,m;

        for(i=N_l;i>0;i--) {
            j = (int) Math.floor(Math.random()*i);
            for(k=0;k<=j;k++) if(N[k]==-1) j++;
            I=(N[j]-1)/9; J=(N[j]-1)%9;

            sw[I][J]=0;
            for(k=0;k<9;k++) {
                for(l=0;l<9;l++) {
                    for(m=0;m<9;m++) s3D[k][l][m]=0;
                    s3D_l[k][l]=0;
                }
            }
            allp();
            nos=nos_limited(0,0,0);
            if(nos==1) {
                N[j]=-1; N_l--;
                if(remove_numbers(N, N_l,x+1)!=0) return 1;
            }
            sw[I][J]=sa[I][J];
        }
        return 0;
    }

    public void generate_puzzle(int level) {
        if(level==1) nob=30;
        else if(level==2) nob=45;
        else if(level==3) nob=55;

        int i,j,k;
        int[] N = new int[81];

        for(i=0;i<9;i++) {
            for(j=0;j<9;j++) {
                sw[i][j]=0; sa[i][j]=0; sq[i][j]=0;
                s3D_l[i][j]=0;
                for(k=0;k<9;k++) s3D[i][j][k]=0;
            }
        }
        allp();
        generate_solved_puzzle(0,0);

        for(i=0;i<9;i++) for(j=0;j<9;j++) sa[i][j]=sw[i][j];
        for(i=0;i<81;i++) N[i]=i+1;
        remove_numbers(N, 81,0);
        for(i=0;i<9;i++) for(j=0;j<9;j++) sq[i][j]=sw[i][j];

        print_q();
        lock_status=2;
    }

    public void set_puzzle() {
        int i,j;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
        List<Table_1> recievedTable = dataBaseHelper.getAll_T1();
        for(i=1;i<=81;i++) {
            sq[(i-1)/9][(i-1)%9] = recievedTable.get(i-1).getSq();
            sa[(i-1)/9][(i-1)%9] = recievedTable.get(i-1).getSa();
            sw[(i-1)/9][(i-1)%9] = recievedTable.get(i-1).getSw();
            se[(i-1)/9][(i-1)%9] = recievedTable.get(i-1).getSe();
            se3d[(i-1)/9][(i-1)%9][0] = recievedTable.get(i-1).getSe3d_1();
            se3d[(i-1)/9][(i-1)%9][1] = recievedTable.get(i-1).getSe3d_2();
            se3d[(i-1)/9][(i-1)%9][2] = recievedTable.get(i-1).getSe3d_3();
            se3d[(i-1)/9][(i-1)%9][3] = recievedTable.get(i-1).getSe3d_4();
            se3d[(i-1)/9][(i-1)%9][4] = recievedTable.get(i-1).getSe3d_5();
            se3d[(i-1)/9][(i-1)%9][5] = recievedTable.get(i-1).getSe3d_6();
            se3d[(i-1)/9][(i-1)%9][6] = recievedTable.get(i-1).getSe3d_7();
            se3d[(i-1)/9][(i-1)%9][7] = recievedTable.get(i-1).getSe3d_8();
            se3d[(i-1)/9][(i-1)%9][8] = recievedTable.get(i-1).getSe3d_9();
        }
        List<Table_2> recievedTable2 = dataBaseHelper.getAll_T2();
        received_level = recievedTable2.get(0).getLevel();
        received_time = recievedTable2.get(0).getTime();
        if(received_level !=0) level.setText("Level - "+String.valueOf(received_level));
        else level.setText("Custom Puzzle");

        print_q();
        lock_status=2;
        for(i=1;i<=81;i++) {
            set_cell_text(se[(i-1)/9][(i-1)%9], i);
        }
        for(i=1;i<=81;i++) {
            for(j=0;j<9;j++) {
                if(se3d[(i-1)/9][(i-1)%9][j] != 0) cellIn[i][j+1].setText(String.valueOf(se3d[(i-1)/9][(i-1)%9][j]));
            }
        }
        if(running) {
            timer.stop();
            running = false;
        }
        if(!running) {
            timer.setBase(SystemClock.elapsedRealtime() - received_time);
            timer.start();
            running = true;
        }
    }

    public void solve(View view) {
        print_a();
    }
    public void hint(View view) {
        if(more_tools.getVisibility() != View.INVISIBLE) more_tools.startAnimation(animToTop);
        if( (current_cell != 0) && (sq[(current_cell-1)/9][(current_cell-1)%9] == 0) )  set_cell_text(sa[(current_cell-1)/9][(current_cell-1)%9], current_cell);
    }
    public void validation(View view) {
        more_tools.startAnimation(animToTop);

        if( String.valueOf(validation_status.getText()).equals("ON") ) validation_status.setText("OFF");
        else validation_status.setText("ON");

        int i,n;
        String str;

        if( String.valueOf(validation_status.getText()).equals("ON") ) {
            for(i=1;i<=81;i++) {
                if(sq[(i-1)/9][(i-1)%9] != 0) continue;
                str = String.valueOf(cell[i].getText());
                if(str.equals("")) continue;
                n=Integer.parseInt(str);
                if(n == sa[(i-1)/9][(i-1)%9]) {
                    cell[i].setTextColor(Color.parseColor("#43B524"));
                }
                else {
                    cell[i].setTextColor(Color.parseColor("#E82121"));
                }
            }
        }
        else {
            for(i=1;i<=81;i++) {
                if(sq[(i-1)/9][(i-1)%9] != 0) continue;
                cell[i].setTextColor(this.getResources().getColor(R.color.grey));
            }
        }
    }
    public void clear(View view) {
        more_tools.startAnimation(animToTop);
        current_cell=0;
        validation_status.setText("OFF");
        notes_status.setText("OFF");
        int i,j;
        for(i=1;i<=81;i++) {
            cellBack[i].setBackgroundResource(R.drawable.background_2);
            if(sq[(i-1)/9][(i-1)%9] == 0) {
                set_cell_text(0, i);
                for(j=1;j<=9;j++) {
                    cellIn[i][j].setText("");
                    cellIn[i][j].setTextColor(this.getResources().getColor(R.color.black));
                }
            }
        }
    }
    public void backspace(View view) {
        int i;
        if( (current_cell != 0) && (sq[(current_cell-1)/9][(current_cell-1)%9] == 0) )  {
            set_cell_text(0, current_cell);
            for(i=1;i<=9;i++) cellIn[current_cell][i].setText("");
        }
    }
    public void Refresh() {
        current_cell=0;
        validation_status.setText("OFF");
        notes_status.setText("OFF");
        int i,j,k;
        for(i=1;i<=81;i++) {
            cellBack[i].setBackgroundResource(R.drawable.background_2);
            set_cell_text(0, i);
            for(j=1;j<=9;j++) {
                cellIn[i][j].setText("");
                cellIn[i][j].setTextColor(this.getResources().getColor(R.color.black));
            }
        }
        for(i=0;i<9;i++) {
            for(j=0;j<9;j++) {
                sw[i][j]=0;
                se[i][j]=0;
                for(k=0;k<9;k++) {
                    se3d[i][j][k]=0;
                }
            }
        }
    }

    public int lock(View view) {
        if(lock_cross.getVisibility() == view.VISIBLE) return 0;
        lock_status=2;
        primary_tools.setVisibility(View.VISIBLE);
        pre_tools.setVisibility(View.INVISIBLE);
        no_of_solutions.setVisibility(View.INVISIBLE);
        level.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        if(game_mode.equals("appa")) {
            mistakes.setVisibility(View.VISIBLE);
            validation(dummy);
        }

        if(running) {
            timer.stop();
            running = false;
        }
        if(!running) {
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            running = true;
        }

        int i,j,k,con_q=0,nos=0;

        for(i=0;i<9;i++) {
            for(j=0;j<9;j++) {
                sw[i][j]=0; sa[i][j]=0; sq[i][j]=0;
                s3D_l[i][j]=0;
                for(k=0;k<9;k++) s3D[i][j][k]=0;
            }
        }

        for(i=1; i<=81; i++) {
            if(String.valueOf(cell[i].getText()).equals("")) sw[(i-1)/9][(i-1)%9] = 0;
            else sw[(i-1)/9][(i-1)%9] = Integer.parseInt(String.valueOf(cell[i].getText()));
            sq[(i-1)/9][(i-1)%9] = sw[(i-1)/9][(i-1)%9];
            if(sw[(i-1)/9][(i-1)%9] != 0) {
                s3D[(i-1)/9][(i-1)%9][0] = sw[(i-1)/9][(i-1)%9];
                s3D_l[(i-1)/9][(i-1)%9]++;
                if(con_q == 0) con_q=con((i-1)/9,(i-1)%9);
            }
        }

        if(con_q==0) {
            allp();
            nos=nos_limited(0,0,0);
            get_answer(0,0);
        }
        for(i=0;i<9;i++) for(j=0;j<9;j++) sa[i][j]=sw[i][j];
        print_q();
        return 0;
    }

    public void pre_solve(View view) {
        int i,j,k,con_q=0,nos=0,x,complete_status=1;

        pre_tools.setVisibility(View.INVISIBLE);
        no_of_solutions.setVisibility(View.INVISIBLE);
        keys.setVisibility(View.INVISIBLE);
        back_to_home.setVisibility(View.VISIBLE);

        for(i=0;i<9;i++) {
            for(j=0;j<9;j++) {
                sw[i][j]=0; sa[i][j]=0; sq[i][j]=0;
                s3D_l[i][j]=0;
                for(k=0;k<9;k++) s3D[i][j][k]=0;
            }
        }

        for(i=1; i<=81; i++) {
            if(String.valueOf(cell[i].getText()).equals("")) sw[(i-1)/9][(i-1)%9] = 0;
            else sw[(i-1)/9][(i-1)%9] = Integer.parseInt(String.valueOf(cell[i].getText()));
            sq[(i-1)/9][(i-1)%9] = sw[(i-1)/9][(i-1)%9];
            if(sw[(i-1)/9][(i-1)%9] != 0) {
                cell[i].setTextColor(this.getResources().getColor(R.color.black));
                s3D[(i-1)/9][(i-1)%9][0] = sw[(i-1)/9][(i-1)%9];
                s3D_l[(i-1)/9][(i-1)%9]++;
                if(con_q == 0) con_q=con((i-1)/9,(i-1)%9);
            }
        }

        if(con_q==0) {
            allp();
            nos=nos_limited(0,0,0);
            get_answer(0,0);
        }
        for(i=0;i<9;i++) for(j=0;j<9;j++) sa[i][j]=sw[i][j];
        print_q();
        print_a_2();
    }

    public void end(View view) { finish(); }

    public void finalise(View view) {
        finalise_status=1;
        if(success_state == 1) {
            success.setVisibility(View.VISIBLE);
            success.startAnimation(animFadeIn);
            total_time.setText("Total Time  :  " + String.valueOf(timer.getText()) );
        }
        else {
            failure.setVisibility(View.VISIBLE);
            failure.startAnimation(animFadeIn);
        }
        finish_template.setVisibility(View.VISIBLE);
    }

    public void notes(View view) {
        if( String.valueOf(notes_status.getText()).equals("ON") ) notes_status.setText("OFF");
        else notes_status.setText("ON");
    }

    public void more(View view) {
            if(more_tools.getVisibility() == View.VISIBLE) {
                more_tools.startAnimation(animToTop);
            }
            else {
                more_tools.setVisibility(View.VISIBLE);
                more_tools.startAnimation(animFromTop);
                keys.setVisibility(View.INVISIBLE);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        int i,j;
        String str;

        level = (TextView) findViewById(R.id.level);
        timer = (Chronometer) findViewById(R.id.timer);

        mistakes_no = (TextView) findViewById(R.id.mistakes_no);
        mistakes = (ConstraintLayout) findViewById(R.id.mistakes);

        primary_tools = (ConstraintLayout) findViewById(R.id.primary_tools);
        more_tools = (ConstraintLayout) findViewById(R.id.more_tools);
        pre_tools = (ConstraintLayout) findViewById(R.id.pre_tools);
        keys = (ConstraintLayout) findViewById(R.id.keys);

        more_button = (ConstraintLayout) findViewById(R.id.more_button);
        hint_2 = (ConstraintLayout) findViewById(R.id.hint_2);
        notes = (ConstraintLayout) findViewById(R.id.notes);
        notes_status = (TextView) findViewById(R.id.notes_status);
        backspace = (ConstraintLayout) findViewById(R.id.backspace);

        solve = (ConstraintLayout) findViewById(R.id.solve);
        hint = (ConstraintLayout) findViewById(R.id.hint);
        validation = (ConstraintLayout) findViewById(R.id.validation);
        validation_status = (TextView) findViewById(R.id.validation_status);
        clear = (ConstraintLayout) findViewById(R.id.clear);

        lock = (ConstraintLayout) findViewById(R.id.lock);
        lock_cross = (ImageView) findViewById(R.id.lock_cross);
        pre_solve = (ConstraintLayout) findViewById(R.id.pre_solve);
        pre_clear = (ConstraintLayout) findViewById(R.id.pre_clear);
        pre_backspace = (ConstraintLayout) findViewById(R.id.pre_backspace);

        no_of_solutions = (TextView) findViewById(R.id.no_of_solutions);
        back_to_home = (ConstraintLayout) findViewById(R.id.back_to_home);
        complete_msg = (TextView) findViewById(R.id.complete_msg);
        finish = (Button) findViewById(R.id.finish);
        back_to_home_2 = (ConstraintLayout) findViewById(R.id.back_to_home_2);
        finish_template = (ConstraintLayout) findViewById(R.id.finish_template);
        total_time = (TextView) findViewById(R.id.total_time);
        success = (ConstraintLayout) findViewById(R.id.success);
        failure = (ConstraintLayout) findViewById(R.id.failure);

        status_out = (TextView) findViewById(R.id.status_out);

        dummy = (View) findViewById(R.id.dummy);

        animFromTop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_top);
        animToTop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_top);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        animToTop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                more_tools.setVisibility(View.INVISIBLE);
                keys.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        for(i=1;i<=9;i++) {
            str = "key_" + String.valueOf(i);
            key[i] = (Button) findViewById(getResources().getIdentifier(str, "id", getPackageName()));
        }
        for(i=1;i<=81;i++) {
            for(j=1;j<=9;j++) {
                str = "cell_" + String.valueOf(i) + "_" + String.valueOf(j);
                cellIn[i][j] = (TextView) findViewById(getResources().getIdentifier(str, "id", getPackageName()));
            }
            str = "cell_" + String.valueOf(i) + "_text";
            cell[i] = (TextView) findViewById(getResources().getIdentifier(str, "id", getPackageName()));
            str = "cell_" + String.valueOf(i);
            cellBack[i] = (ConstraintLayout) findViewById(getResources().getIdentifier(str, "id", getPackageName()));
        }

        for(i=1;i<=81;i++) {
            int I=i;
            cell[I].setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if(current_cell != 0) cellBack[current_cell].setBackgroundResource(R.drawable.background_2);
                    current_cell = I;
                    cellBack[current_cell].setBackgroundResource(R.drawable.background_3);
                    return true;
                }
            });
        }

        for(i=1;i<=9;i++) {
            int I=i;
            key[I].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if( (current_cell != 0) && (sq[(current_cell-1)/9][(current_cell-1)%9] == 0) ) {
                        if( String.valueOf(notes_status.getText()).equals("ON") ) {
                            cell[current_cell].setText("");
                            sw[(current_cell-1)/9][(current_cell-1)%9]=0;
                            if(String.valueOf(cellIn[current_cell][I].getText()).equals("") ) cellIn[current_cell][I].setText(String.valueOf(I));
                            else cellIn[current_cell][I].setText("");
                        }
                        else {
                            set_cell_text(I, current_cell);
                        }
                    }
                }
            });
        }

        Refresh();
        received_level = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));

        if(received_level == 4) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
            List<Table_2> recievedTable2 = dataBaseHelper.getAll_T2();
            no_mistakes = recievedTable2.get(0).getMistakes();
            game_mode = recievedTable2.get(0).getGame_mode();
            if(game_mode.equals("raw")) {
                more_button.setVisibility(View.GONE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(notes);
                constraintSet.connect(R.id.notes,ConstraintSet.LEFT,R.id.primary_tools,ConstraintSet.LEFT,0);
                constraintSet.applyTo(notes);
            }
            else if(game_mode.equals("appa")) {
                validation(dummy);
                more_button.setVisibility(View.INVISIBLE);
                hint_2.setVisibility(View.VISIBLE);
                mistakes.setVisibility(View.VISIBLE);
            }
        }
        else {
            game_mode="default";
            no_mistakes=0;

            DataBaseHelper dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
            List<Table_3> recievedTable = dataBaseHelper.getAll_T3();

            if(recievedTable.size() != 0)  {
                game_mode = String.valueOf(recievedTable.get(0).getGame_mode());
                if(game_mode.equals("raw")) {
                    more_button.setVisibility(View.GONE);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(notes);
                    constraintSet.connect(R.id.notes,ConstraintSet.LEFT,R.id.primary_tools,ConstraintSet.LEFT,0);
                    constraintSet.applyTo(notes);
                }
                else if(game_mode.equals("appa")) {
                    more_button.setVisibility(View.INVISIBLE);
                    hint_2.setVisibility(View.VISIBLE);
                    if(received_level==0) {
                        mistakes.setVisibility(View.INVISIBLE);
                    }
                    else {
                        validation(dummy);
                        mistakes.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

        if(received_level == 0) {
            level.setText("Custom Puzzle");
            pre_tools.setVisibility(View.VISIBLE);
            no_of_solutions.setVisibility(View.VISIBLE);
            level.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.INVISIBLE);
        }
        else {
            lock_status=1;
            level.setText("Level - " + String.valueOf(received_level));
            primary_tools.setVisibility(View.VISIBLE);
            if(received_level == 4) {
                set_puzzle();
            }
            else {
                generate_puzzle(received_level);
            }
        }

        mistakes_no.setText(String.valueOf(no_mistakes));

        if(!running) {
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(lock_status == 2) {
            Table_1 table_1;
            boolean success=true;
            DataBaseHelper dataBaseHelper;

            dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
            dataBaseHelper.deleteAll_T1();

            for(int i=1;i<=81;i++) {
                if(String.valueOf(cell[i].getText()).equals("")) se[(i-1)/9][(i-1)%9] = 0;
                else se[(i-1)/9][(i-1)%9] = Integer.parseInt(String.valueOf(cell[i].getText()));
                for(int j=0;j<9;j++) {
                    if(String.valueOf(cellIn[i][j+1].getText()).equals("")) se3d[(i-1)/9][(i-1)%9][j] = 0;
                    else se3d[(i-1)/9][(i-1)%9][j] = Integer.parseInt(String.valueOf(cellIn[i][j+1].getText()));
                }
                table_1 = new Table_1(sq[(i-1)/9][(i-1)%9], sa[(i-1)/9][(i-1)%9], sw[(i-1)/9][(i-1)%9], se[(i-1)/9][(i-1)%9], se3d[(i-1)/9][(i-1)%9][0], se3d[(i-1)/9][(i-1)%9][1], se3d[(i-1)/9][(i-1)%9][2], se3d[(i-1)/9][(i-1)%9][3], se3d[(i-1)/9][(i-1)%9][4], se3d[(i-1)/9][(i-1)%9][5], se3d[(i-1)/9][(i-1)%9][6], se3d[(i-1)/9][(i-1)%9][7], se3d[(i-1)/9][(i-1)%9][8], "", "", "", "", "");
                dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
                success = dataBaseHelper.addOne_T1(table_1);
            }

            Table_2 table_2;
            dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
            dataBaseHelper.deleteAll_T2();

            if(finalise_status == 0) {
                no_mistakes = Integer.parseInt(String.valueOf(mistakes_no.getText()));
                table_2 = new Table_2(0, received_level,"OFF", "OFF", (SystemClock.elapsedRealtime() - timer.getBase()), no_mistakes, game_mode, "", "", "", "", "");
                dataBaseHelper = new DataBaseHelper(PuzzleActivity.this);
                success = dataBaseHelper.addOne_T2(table_2);
            }
        }
    }

    @Override
    protected void onResume() {
            super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_home) end(dummy);

        return super.onOptionsItemSelected(item);
    }
}