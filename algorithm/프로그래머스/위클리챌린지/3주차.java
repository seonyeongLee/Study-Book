/**
* [3주차] https://programmers.co.kr/learn/courses/30/lessons/84021
*/
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public static boolean[][] brdBln;
    public static boolean[][] tblBln;
    public static ArrayList<Integer> brdBlkSize;
    public static ArrayList<Integer> tblBlkSize;
    
    public int solution(int[][] game_board, int[][] table) {
    	int answer = 0;
    	ArrayList<int[][]> brdBlk = new ArrayList<int[][]>(); // game_board space
    	ArrayList<int[][]> tblBlk = new ArrayList<int[][]>(); // table block
    	// ⑴ 게임보드, 테이블의 빈 공간, 블록조각이 이미 추출된 것인지 체크용 배열 선언
    	brdBln = new boolean[game_board.length][game_board.length];
    	tblBln = new boolean[table.length][table.length];
    	
    	// ① 게임보드, 테이블 빈 공간, 블록조각 추출
    	for (int i = 0; i < game_board.length; i++) {
    		int[][] gm_brd = new int[6][6];
    		int[][] tbl = new int[6][6];
    		for (int j = 0; j < game_board.length; j++) {
    			// ⑵ 게임보드의 0인 빈 공간 추출 (추출 후 해당 빈공간의 인덱스 값은 true 처리) 
    			if (game_board[i][j] == 0 && brdBln[i][j] == false) {
    				gm_brd = chkArrBlk(gm_brd, game_board, i, j, 0);
    				brdBlk.add(gm_brd);
    				gm_brd = new int[6][6];
    			}
    			// ⑵ 테이블의 1인 블록조각 추출 (추출 후 해당 블록조각의 인덱스 값은 true 처리) 
    			if (table[i][j] == 1 && tblBln[i][j] == false) {
    				tbl = chkArrBlk(tbl, table, i, j, 1);
    				tblBlk.add(tbl);
    				tbl = new int[6][6];
    			}
    		}
    	}
    	
    	// ⑶ 추출한 빈 공간의 배열 정리 (추출된 배열의 최상단좌측으로 정렬)
    	for (int i = 0; i < brdBlk.size(); i++) {
    		// 가로 정렬
    		int idx = getHorIdxLength(brdBlk.get(i));
    		if (idx > 0 && idx < brdBlk.get(i).length)
    			// 정렬된 해당 배열은 갱신
    			brdBlk.set(i, pshHorArrBlk(brdBlk.get(i), idx));
    		// 세로 정렬
    		idx = getVerIdxLength(brdBlk.get(i));
    		if (idx > 0 && idx < brdBlk.get(i).length)
    			// 정렬된 해당 배열은 갱신
    			brdBlk.set(i, pshVerArrBlk(brdBlk.get(i), idx));
    	}
    	// ⑶ 추출한 블록조각의 배열 정리 (추출된 배열의 최상단좌측으로 정렬)
    	for (int i = 0; i < tblBlk.size(); i++) {
    		// 가로 정렬
    		int idx = getHorIdxLength(tblBlk.get(i));
    		if (idx > 0 && idx < tblBlk.get(i).length)
    			// 정렬된 해당 배열은 갱신
    			tblBlk.set(i, pshHorArrBlk(tblBlk.get(i), idx));
    	    // 세로 정렬
    	    idx = getVerIdxLength(tblBlk.get(i));
    		if (idx > 0 && idx < tblBlk.get(i).length)
    			// 정렬된 해당 배열은 갱신
    			tblBlk.set(i, pshVerArrBlk(tblBlk.get(i), idx));
    	}
    	
    	// ② 빈 공간과 블록조각 비교
    	for (int i = 0; i < brdBlk.size(); i++) {
    		for (int j = 0; j < tblBlk.size(); j++) {
    			// 이미 비교된 블록조각 배열일 경우 다음 블록조각으로 비교 계속
    			if (tblBlk.get(j)[0][0] == -1)
    				continue;
    			int result = cmpArr(brdBlk.get(i), tblBlk.get(j));
    			// ⑵ 빈 공간 및 블록조각의 배열이 다를 경우
    			if (result == 0) {
    				// ⒜ 해당 블록조각의 배열 90◦회전
    				// ⒝ ②반복
    				// ⒞ 해당 블록조각의 모든 회전방향이 맞지 않을 경우, 다음 블록조각 비교
    				tblBlk.set(j, rttArr(tblBlk.get(j)));
    				result = cmpArr(brdBlk.get(i), tblBlk.get(j));
    				if (result == 0) {
    					// rotate 180
    					tblBlk.set(j, rttArr(tblBlk.get(j)));
    					result = cmpArr(brdBlk.get(i), tblBlk.get(j));
    					if (result == 0) {
    						// rotate 270
    						tblBlk.set(j, rttArr(tblBlk.get(j)));
    						result = cmpArr(brdBlk.get(i), tblBlk.get(j));
    						if (result == 0) {
    							continue;
    						}
    					}
    				}
    			}
    			// ⑴ 빈 공간 및 블록조각의 배열이 같을 경우
    			// ⒜ 블록조각의 크기만큼 점수 계산
    			answer += result;
    			// ⒝ 테이블의 블록조각 리스트에서 해당 블록조각 삭제
    			tblBlk.set(j, new int[][] { { -1 } });
    			// 비교된 게임보드의 빈 공간은 더이상 빈 공간이 아니므로 다음 빈 공간으로 이동
    			break;
    		}
    	}
    	
    	// ③ 일치한 블록조각의 합산된 점수 반환
    	return answer;
    }
    
    // 게임보드의 빈 공간 및 테이블의 블록조각 탐색
    // 탐색한 배열을 저장할 2차원 배열(6 * 6), 탐색할 2차원 배열, 탐색한 인덱스, 게임보드와 테이블 구분 flag
    public int[][] chkArrBlk(int[][] temp, int[][] searchArr, int i, int j, int flg) {
    	if (flg == 0) { // game_board
    		// 아직 탐색되지 않은 빈 공간인지 확인
    		if (searchArr[i][j] == flg && brdBln[i][j] == false) {
    			temp[i % 6][j % 6] = 1;
    			// 중복탐색이 되지않도록 중복확인용 배열의 인덱스 값 true 설정
    			brdBln[i][j] = true;
    			// 상하좌우 인덱스가 배열의 범위를 벗어나지 않을 때, 탐색
    			// 탐색은 재귀호출로 시작
    			if (i - 1 >= 0) {
    				if (searchArr[i - 1][j] == flg && brdBln[i - 1][j] == false) {
    					temp = chkArrBlk(temp, searchArr, i - 1, j, flg);
    				}
    			}
    			if (i + 1 < searchArr.length) {
    				if (searchArr[i + 1][j] == flg && brdBln[i + 1][j] == false) {
    					temp = chkArrBlk(temp, searchArr, i + 1, j, flg);
    				}
    			}
    			if (j - 1 >= 0) {
    				if (searchArr[i][j - 1] == flg && brdBln[i][j - 1] == false) {
    					temp = chkArrBlk(temp, searchArr, i, j - 1, flg);
    				}
    			}
    			if (j + 1 < searchArr.length) {
    				if (searchArr[i][j + 1] == flg && brdBln[i][j + 1] == false) {
    					temp = chkArrBlk(temp, searchArr, i, j + 1, flg);
    				}
    			}
    		}
    	} else if (flg == 1) {
    		// 아직 탐색되지 않은 블록조각인지 확인
    		if (searchArr[i][j] == flg && tblBln[i][j] == false) {
    			temp[i % 6][j % 6] = 1;
    			// 중복탐색이 되지않도록 중복확인용 배열의 인덱스 값 true 설정
    			tblBln[i][j] = true;
    			// 상하좌우 인덱스가 배열의 범위를 벗어나지 않을 때, 탐색
    			// 탐색은 재귀호출로 시작
    			if (i - 1 >= 0) {
    				if (searchArr[i - 1][j] == flg && tblBln[i - 1][j] == false) {
    					temp = chkArrBlk(temp, searchArr, i - 1, j, flg);
    				}
    			}
    			if (i + 1 < searchArr.length) {
    				if (searchArr[i + 1][j] == flg && tblBln[i + 1][j] == false) {
    					temp = chkArrBlk(temp, searchArr, i + 1, j, flg);
    				}
    			}
    			if (j - 1 >= 0) {
    				if (searchArr[i][j - 1] == flg && tblBln[i][j - 1] == false) {
    					temp = chkArrBlk(temp, searchArr, i, j - 1, flg);
    				}
    			}
    			if (j + 1 < searchArr.length) {
    				if (searchArr[i][j + 1] == flg && tblBln[i][j + 1] == false) {
    					temp = chkArrBlk(temp, searchArr, i, j + 1, flg);
    				}
    			}
    		}
    	}
    	
    	// 탐색된 배열을 반환
    	return temp;
    }
    
    // 가로 정렬의 기준이 되는 인덱스 길이 반환 (세로열이 0인 공간 정리)
    public int getHorIdxLength(int[][] searchArr) {
    	int idx = searchArr.length;
    	int std = 0; // 0인 세로열의 최소기준점
    	boolean zeroFlg = false; // 최소기준점의 갱신방지용 플래그
    	for (int i = 0; i < searchArr.length; i++) {
    		for (int j = 0; j < searchArr.length; j++) {
    			if (searchArr[i][j] == 1 && zeroFlg) {
    				if (idx > j && j > std) {
    					// 0인 세로열 다음의 1인 가장 작은 인덱스 갱신
    					idx = j;
    				}
    			} else if (searchArr[i][j] == 0 && !zeroFlg) {
    				int sum = 0;
    				for (int k = 0; k < searchArr.length; k++) {
    					if (searchArr[k][j] == 1) {
    						break;
    					}
    					sum += 1;
    				}
    				if (sum == searchArr.length) {
    					zeroFlg = true;
    					std = j;
    				}
    			}
    		}
    	}
    
    	// 0인 세로열 다음의 1인 세로열 길이 반환
    	return searchArr.length - idx;
    }
    
    // 세로 정렬의 기준이 되는 인덱스 길이 반환 (가로행이 0인 공간 정리)
    public int getVerIdxLength(int[][] searchArr) {
    	int idx = searchArr.length;
    	boolean zeroFlg = false; // 0인 가로행 확인용 플래그
    	for (int i = 0; i < searchArr.length; i++) {
    		for (int j = 0; j < searchArr.length; j++) {
    			if (searchArr[i][j] == 1 && zeroFlg) {
    				if (idx > i) {
    					// 0인 가로행 다음의 1인 가장 작은 인덱스 갱신
    					idx = i;
    				}
    			} else if (searchArr[i][j] == 0 && !zeroFlg) {
    				int sum = 0;
    				for (int k = 0; k < searchArr.length; k++) {
    					if (searchArr[i][k] == 1) {
    						break;
    					}
    					sum += 1;
    				}
    				if (sum == searchArr.length)
    					zeroFlg = true;
    			}
    		}
    	}
    
    	// 0인 가로행 다음의 1인 가로행 길이 반환
    	return searchArr.length - idx;
    }
    
    // 0인 세로열 다음의 1인 세로행 길이 만큼 옮기는 작업
    public int[][] pshHorArrBlk(int[][] pushArr, int length) {
    	int[][] rtnArr = new int[pushArr.length][pushArr.length];
    
    	for (int i = 0; i < pushArr.length; i++) {
    		System.arraycopy(pushArr[i], pushArr[i].length - length, rtnArr[i], 0, length);
    		System.arraycopy(pushArr[i], 0, rtnArr[i], length, rtnArr[i].length - length);
    	}
    
    	return rtnArr;
    }
    
    // 0인 가로행 다음의 1인 가로행 길이 만큼 옮기는 작업
    public int[][] pshVerArrBlk(int[][] pushArr, int length) {
    	int[][] rtnArr = new int[pushArr.length][pushArr.length];
    
    	for (int i = 0; i < pushArr.length; i++) {
    		System.arraycopy(pushArr[(i + (pushArr.length - length)) % 6], 0, rtnArr[i], 0, pushArr.length);
    	}
    
    	return rtnArr;
    }
    
    // 게임보드의 빈 공간과 테이블의 블록조각의 추출된 배열 비교
    public int cmpArr(int[][] gm_brd, int[][] tbl) {
    	int sum = 0;
    	// 인덱스의 값들이 완전 일치할 경우
    	if (Arrays.deepEquals(gm_brd, tbl) == true) {
    		for (int i = 0; i < gm_brd.length; i++) {
    			// 빈 공간(또는 블록조각)의 크기만큼 점수 계산
    			sum += Arrays.stream(gm_brd[i]).sum();
    		}
    	}
    	// 계산된 점수 반환(일치하지 않을 경우 0 반환)
    	return sum;
    }
    
    // 입력된 배열을 오른쪽으로 90◦ 회전
    public int[][] rttArr(int[][] arr) {
    	int[][] rotateArr = new int[arr.length][arr.length];
    
    	// 입력된 배열을 회전
    	for (int i = 0; i < arr.length; i++) {
    		for (int j = 0; j < arr.length; j++) {
    			rotateArr[i][j] = arr[arr.length - 1 - j][i];
    		}
    	}
    	// 회전된 배열의 0인 가로행, 세로열의 정리(최상단좌측 정렬)
    	int idx = getHorIdxLength(rotateArr);
    	if (idx > 0 && idx < rotateArr.length)
    		rotateArr = pshHorArrBlk(rotateArr, idx);
    	idx = getVerIdxLength(rotateArr);
    	if (idx > 0 && idx < rotateArr.length)
    		rotateArr = pshVerArrBlk(rotateArr, idx);
    
    	// 회전 및 정렬된 배열 반환
    	return rotateArr;
    }
}
