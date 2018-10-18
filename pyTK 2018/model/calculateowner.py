import sqlite3

from team import *

conn = sqlite3.connect('C:\\Workspace_2018\\pyTK 2018\\scouting.db')
c = conn.cursor()

#indexes for Ownership Table
oMatchNum = 2
oBalance = 3
oOwner = 4
oTime = 5

#indexes for Cube Table
cMatchNum = 2
cTeamNum = 3
cBalance = 4
cTime = 5
cPhase = 6

#indexes for Stats Table
sMatchNum = 1
sTeamNum = 2
sMatchPos = 3

def sort_ownership():
	global c
	global oMatchNum 
	global oBalance 
	global oOwner 
	global oTime 
	global cMatchNum
	global cTeamNum
	global cBalance
	global cTime
	global cPhase
	global sMatchNum
	global sTeamNum
	global sMatchPos
	
	c.execute('SELECT MatchNumber FROM StatsAll')
	matchNums = c.fetchall()
	lastmatch = 0
	#get the number of matches
	for i in matchNums:
                if i[0] > lastmatch:
                        lastmatch = i[0]
	print("The last match is: " + str(lastmatch))
	#process data for each match
	count = 1
	while count < lastmatch+1:
	
                c.execute('SELECT * FROM OwnershipAll WHERE MatchNumber = ' + str(count))
		ownerships = []
		rowOwn = c.fetchone()
		#get all changes in ownership for the current match 
		while rowOwn is not None:
			ownerships.append(rowOwn)
			rowOwn = c.fetchone()
		autoOwn = []
		teleOwn = []
		
		#separate ownership changes into auto and tele
		for o in ownerships:
			if time_min_to_sec(o[oTime]) > 135.0:
				autoOwn.append(o)
			else:
				teleOwn.append(o)
		
		#sort the ownerships by balance
		autoOwnSw1 = sort_by_balance('Own',autoOwn,'Sw1')
		autoOwnScl = sort_by_balance('Own',autoOwn,'Scl')
		autoOwnSw2 = sort_by_balance('Own',autoOwn,'Sw2')
		teleOwnSw1 = sort_by_balance('Own',teleOwn,'Sw1')
		teleOwnScl = sort_by_balance('Own',teleOwn,'Scl')
		teleOwnSw2 = sort_by_balance('Own',teleOwn,'Sw2')

		autoNumOwnChangesSw1 = len(autoOwnSw1)
		autoNumOwnChangesScl = len(autoOwnScl)
		autoNumOwnChangesSw2 = len(autoOwnSw2)
		
		teleNumOwnChangesSw1 = len(teleOwnSw1)
                teleNumOwnChangesScl = len(teleOwnScl)
		teleNumOwnChangesSw2 = len(teleOwnSw2)
		
		c.execute('SELECT * FROM CubesAll WHERE MatchNumber = ' + str(count))
		cubes = []
		rowCube = c.fetchone()
		#get all cubes scored in this match
		while rowCube is not None:
			#print (rowCube)
			cubes.append(rowCube)
			rowCube = c.fetchone()
		
		autoCube = []
		teleCube = []
		
		#separate cubes into auto and tele
		for b in cubes:
			if b[cPhase] == 'Auto':
				autoCube.append(b)
			else:
				teleCube.append(b)
		
		#sort the cubes by balance
		autoCubeSw1 = sort_by_balance('Cube',autoCube,'Sw1')
		autoCubeScl = sort_by_balance('Cube',autoCube,'Scl')
		autoCubeSw2 = sort_by_balance('Cube',autoCube,'Sw2')
		teleCubeSw1 = sort_by_balance('Cube',teleCube,'Sw1')
		teleCubeScl = sort_by_balance('Cube',teleCube,'Scl')
		teleCubeSw2 = sort_by_balance('Cube',teleCube,'Sw2')
		
		#get and sort the teams for the current match into their alliances
		c.execute("""SELECT StatsAll.MatchNumber, StatsAll.MatchPosition, StatsAll.TeamNumber 
			From StatsAll Join CubesAll
			ON StatsAll.MatchNumber = CubesAll.MatchNumber 
			AND StatsAll.TeamNumber = CubesAll.TeamNumber
			WHERE StatsAll.MatchNumber = """ + str(count))
			
		teamPos = c.fetchall()
		
		redTeams = []
		blueTeams = []
		allTeams = []
		for t in teamPos:
			if 3 >= t[1] and t[1] >= 1:
				redTeams.append(t[2])
			else:
				blueTeams.append(t[2])

			allTeams.append(t[2])
		
		redTeams = list(set(redTeams))
		blueTeams = list(set(blueTeams))
                allTeams = list(set(allTeams))

                for sTeam in Team.team_list:
                        for sT in redTeams:
                                if sTeam.number == sT:
                                        sTeam.Scores.autoNumOwnChangesSw1.append(autoNumOwnChangesSw1)
                                        sTeam.Scores.autoNumOwnChangesScl.append(autoNumOwnChangesScl)
                                        sTeam.Scores.autoNumOwnChangesSw2.append(autoNumOwnChangesSw2)
                                        sTeam.Scores.teleNumOwnChangesSw1.append(teleNumOwnChangesSw1)
                                        sTeam.Scores.teleNumOwnChangesScl.append(teleNumOwnChangesScl)
                                        sTeam.Scores.teleNumOwnChangesSw2.append(teleNumOwnChangesSw2)

                for sTeam in Team.team_list:
                        for sT in blueTeams:
                                if sTeam.number == sT:
                                        sTeam.Scores.autoNumOwnChangesSw1.append(autoNumOwnChangesSw2)
                                        sTeam.Scores.autoNumOwnChangesScl.append(autoNumOwnChangesScl)
                                        sTeam.Scores.autoNumOwnChangesSw2.append(autoNumOwnChangesSw1)
                                        sTeam.Scores.teleNumOwnChangesSw1.append(teleNumOwnChangesSw2)
                                        sTeam.Scores.teleNumOwnChangesScl.append(teleNumOwnChangesScl)
                                        sTeam.Scores.teleNumOwnChangesSw2.append(teleNumOwnChangesSw1)
                
                
                               
		#get time it takes for ownership to be gained, the time at which it is gained
		#and the time a balance is owned for each alliance
		autoTimeToGainAndOwnSw1 = get_time_to_gained_and_owned(autoOwnSw1, "Auto")
		autoTimeToGainAndOwnScl = get_time_to_gained_and_owned(autoOwnScl, "Auto")
		autoTimeToGainAndOwnSw2 = get_time_to_gained_and_owned(autoOwnSw2, "Auto")
		teleTimeToGainAndOwnSw1 = get_time_to_gained_and_owned(teleOwnSw1, "Tele", autoOwnSw1[len(autoOwnSw1)-1][oOwner]
                                                                       if len(autoOwnSw1) else "None", time_min_to_sec(autoOwnSw1[len(autoOwnSw1)-1][oTime]) if len(autoOwnSw1) else 135.0)
		teleTimeToGainAndOwnScl = get_time_to_gained_and_owned(teleOwnScl, "Tele", autoOwnScl[len(autoOwnScl)-1][oOwner]
                                                                       if len(autoOwnScl) else "None", time_min_to_sec(autoOwnScl[len(autoOwnScl)-1][oTime]) if len(autoOwnScl) else 135.0)
		teleTimeToGainAndOwnSw2 = get_time_to_gained_and_owned(teleOwnSw2, "Tele", autoOwnSw2[len(autoOwnSw2)-1][oOwner]
                                                                       if len(autoOwnSw2) else "None", time_min_to_sec(autoOwnSw2[len(autoOwnSw2)-1][oTime]) if len(autoOwnSw2) else 135.0)
		
		pass_to_Team("Auto","Sw1", autoCubeSw1, autoTimeToGainAndOwnSw1, redTeams, blueTeams)
		pass_to_Team("Auto","Scl", autoCubeScl, autoTimeToGainAndOwnScl, redTeams, blueTeams)
		pass_to_Team("Auto","Sw2", autoCubeSw2, autoTimeToGainAndOwnSw2, redTeams, blueTeams)
		pass_to_Team("Tele","Sw1", teleCubeSw1, teleTimeToGainAndOwnSw1, redTeams, blueTeams)
		pass_to_Team("Tele","Scl", teleCubeScl, teleTimeToGainAndOwnScl, redTeams, blueTeams)
		pass_to_Team("Tele","Sw2", teleCubeSw2, teleTimeToGainAndOwnSw2, redTeams, blueTeams)

		redGainIndex = 0
                redGainedAtIndex = 1
                redOwnIndex = 2
                blueGainIndex = 3
                blueGainedAtIndex = 4
                blueOwnIndex = 5

		for sTeam in Team.team_list:
                        for sT in redTeams:
                                if sTeam.number == sT:
                                                sTeam.Scores.autoOwnedSw1.append(get_totOwnTime(autoTimeToGainAndOwnSw1[redOwnIndex]))                                                                           
                                                sTeam.Scores.autoOwnedSw2.append(get_totOwnTime(autoTimeToGainAndOwnSw2[redOwnIndex]))
                                                sTeam.Scores.autoOwnedScl.append(get_totOwnTime(autoTimeToGainAndOwnScl[redOwnIndex]))
                                                sTeam.Scores.teleOwnedSw1.append(get_totOwnTime(teleTimeToGainAndOwnSw1[redOwnIndex]))
                                                sTeam.Scores.teleOwnedScl.append(get_totOwnTime(teleTimeToGainAndOwnScl[redOwnIndex]))
                                                sTeam.Scores.teleOwnedSw2.append(get_totOwnTime(teleTimeToGainAndOwnSw2[redOwnIndex]))
                for sTeam in Team.team_list:
                        for sT in blueTeams:
                                if sTeam.number == sT:
                                                sTeam.Scores.autoOwnedSw2.append(get_totOwnTime(autoTimeToGainAndOwnSw1[blueOwnIndex]))
                                                sTeam.Scores.autoOwnedSw1.append(get_totOwnTime(autoTimeToGainAndOwnSw2[blueOwnIndex]))
                                                sTeam.Scores.autoOwnedScl.append(get_totOwnTime(autoTimeToGainAndOwnScl[blueOwnIndex]))
                                                sTeam.Scores.teleOwnedSw2.append(get_totOwnTime(teleTimeToGainAndOwnSw1[blueOwnIndex]))
                                                sTeam.Scores.teleOwnedScl.append(get_totOwnTime(teleTimeToGainAndOwnScl[blueOwnIndex]))
                                                sTeam.Scores.teleOwnedSw1.append(get_totOwnTime(teleTimeToGainAndOwnSw2[blueOwnIndex]))

		count +=1

def get_totOwnTime(own):
        time = 0
        if len(own):
                for t in own:
                        time += t
        return time
                
		
			
def pass_to_Team(phase, balance, cubes, times, redTeams, blueTeams):
	global cTime
	global cTeamNum
	#indexes to access arrays returned by get_time_to_gained_and_owned function
	redGainIndex = 0
	redGainedAtIndex = 1
	redOwnIndex = 2
	blueGainIndex = 3
	blueGainedAtIndex = 4
	blueOwnIndex = 5
	
	for t in times[redGainedAtIndex]:
		for c in cubes:
                        if phase == "Auto":
                                if (time_min_to_sec(c[cTime])+135.0) <= t+8 or (time_min_to_sec(c[cTime])+135.0) >= t-8:
                                        counter = 0
                                        for team in redTeams:
                                                if c[cTeamNum] == team:
                                                        for teams in Team.team_list:
                                                                if teams.number == c[cTeamNum]:
                                                                        #print ("Team Gain: " + str(teams.number))
                                                                        if balance == "Sw1":
										teams.Scores.autoOwnGainSw1.append(times[redGainIndex][counter])
										
									elif balance == "Scl":
										teams.Scores.autoOwnGainScl.append(times[redGainIndex][counter])
										
									else:
										teams.Scores.autoOwnGainSw2.append(times[redGainIndex][counter])
									
			else:
                                #print ("TIMES TO COMPARE " + str(time_min_to_sec(c[cTime])) + " : " + str(t))
                                if time_min_to_sec(c[cTime]) <= t+8 or time_min_to_sec(c[cTime]) >= t-8:
                                        counter = 0
                                        for team in redTeams:
                                                if c[cTeamNum] == team:
                                                        for teams in Team.team_list:
                                                                if teams.number == c[cTeamNum]:
                                                                        #print ("Team Gain: " + str(teams.number))
									if balance == "Sw1":
										teams.Scores.teleOwnGainSw1.append(times[redGainIndex][counter])
										
									elif balance == "Scl":
										teams.Scores.teleOwnGainScl.append(times[redGainIndex][counter])
									
									else:
										teams.Scores.teleOwnGainSw2.append(times[redGainIndex][counter])
									
                                        counter+=1
				
	for t in times[blueGainedAtIndex]:
                for c in cubes:
                        if phase == "Auto":
                                if (time_min_to_sec(c[cTime]) + 135.0) <= t+8 or (time_min_to_sec(c[cTime])+ 135.0) >= t-8:
                                        counter = 0
                                        for team in blueTeams:
                                                if c[cTeamNum] == team:
                                                        for teams in Team.team_list:
                                                                if teams.number == c[cTeamNum]:
                                                                        #print ("Team Gain: " + str(teams.number))
                                                                        #blue is reversed b/c Sw1 and Sw2 for ownership scouting is relative to the red alliance
									if balance == "Sw2":
										teams.Scores.autoOwnGainSw1.append(times[blueGainIndex][counter])
				
									elif balance == "Scl":
										teams.Scores.autoOwnGainScl.append(times[blueGainIndex][counter])

									else:
										teams.Scores.autoOwnGainSw2.append(times[blueGainIndex][counter])
				
			else:
                                #print ("TIMES TO COMPARE " + str(time_min_to_sec(c[cTime])) + " : " + str(t))
                                if time_min_to_sec(c[cTime]) <= t+8 or time_min_to_sec(c[cTime]) >= t-8:
                                        counter = 0
                                        for team in blueTeams:
                                                if c[cTeamNum] == team:
                                                        for teams in Team.team_list:
                                                                if teams.number == c[cTeamNum]:
                                                                        #print ("Team Gain: " + str(teams.number))
                                                                        #blue is reversed b/c Sw1 and Sw2 for ownership scouting is relative to the red alliance
									if balance == "Sw2":
										teams.Scores.teleOwnGainSw1.append(times[blueGainIndex][counter])
										
									elif balance == "Scl":
										teams.Scores.teleOwnGainScl.append(times[blueGainIndex][counter])
										
									else:
										teams.Scores.teleOwnGainSw2.append(times[blueGainIndex][counter])										
                                        counter +=1
	
	
		
def sort_by_balance(type, rows, balance):
	global cBalance
	global oBalance
	balanceVaules = []
	if type == 'Own':
		for r in rows:
			if r[oBalance] == balance:
				balanceVaules.append(r)
	else:
		for r in rows:
			if r[cBalance] == balance:
				balanceVaules.append(r)
	return balanceVaules
	
def get_time_to_gained_and_owned(rows, phase, o = "None", startTime = 135.0):
	global oTime
	global oOwner
	
	redGain = []
	blueGain = []
	redGainedAt = []
	blueGainedAt = []
	redOwn = []
	blueOwn = [] 
	
	rowTimes = []
	
        times = []
        firstTime = 0
        secondTime = 0

        if not (len(rows) == 0):
                if phase == 'Auto':
                        firstTime = 150
                        rowTimes.append(firstTime)
			for r in rows:
                                rowTimes.append(round(time_min_to_sec(r[oTime]), 2))
                        #print ("TIMES" + str(rowTimes))
                        count = 0
                        while count < len(rows):
                                times.append(round(rowTimes[count] - rowTimes[count+1], 2))
                                count+=1
                        #print ("TIMES SORTED" + str(times))
			
                        owners = []
                        owners.append(o)
                        for r in rows:
                                owners.append(r[oOwner])

                        #print("OWNER" + str(owners))
			
                        count2 = 1
                        timeIndex = 0
                        redTimeGain = 0
			blueTimeGain = 0
			redHasOwn = True if o == "Red" else False
			blueHasOwn = True if o == "Blue" else False
		
                        while count2 < len(owners):
                                if redHasOwn == False and blueHasOwn == False:
                                        if owners[count2] == "Red":
                                                redGain.append(times[timeIndex])
                                                redGainedAt.append(rowTimes[count2])
						redHasOwn = True
                                                if owners[count2-1] == "Blue":
                                                        blueOwn.append(times[timeIndex])
                                                        blueHasOwn = False
                                                elif owners[count2-1] == "None":
                                                        blueTimeGain += times[timeIndex]
                                        elif owners[count2] == "Blue":
                                                blueGain.append(times[timeIndex])
                                                blueGainedAt.append(rowTimes[count2])
                                                blueHasOwn = True
                                                if owners[count2-1] == "Red":
                                                        redOwn.append(times[timeIndex])
                                                        redHasOwn = False
                                                elif owners[count2-1] == "None":
                                                        redTimeGain += times[timeIndex]
                                        timeIndex+=1
				elif redHasOwn == True:
                                        if owners[count2-1] == "Red":
                                                if owners[count2] == "Blue":
                                                        redOwn.append(times[timeIndex])
                                                        redHasOwn = False
							blueHasOwn = True
                                                        blueTimeGain += times[timeIndex]
                                                        blueGainedAt.append(rowTimes[count2])
                                                        blueGain.append(round(blueTimeGain,2))
                                                        blueTimeGain = 0
						elif owners[count2] == "None":
							blueTimeGain += times[timeIndex]
						timeIndex+=1
					elif owners[count2-1] == "None":
                                                blueTimeGain += times[timeIndex]
                                                if owners[count2] == "Red":
							redGain.append(times[timeIndex])
							redGainedAt.append(rowTimes[count2])
						elif owners[count2] == "Blue":
                                                        blueGain.append(round(blueTimeGain, 2))
							blueGainedAt.append(rowTimes[count2])
							blueTimeGain = 0
							blueHasOwn = True
							redHasOwn = False			
				elif blueHasOwn == True:
                                        if owners[count2-1] == "Blue":
                                                if owners[count2] == "Red":
                                                        blueOwn.append(times[timeIndex])
							blueHasOwn = False
							redHasOwn = True
							redTimeGain += times[timeIndex]
                                                        redGainedAt.append(rowTimes[count2])
							redGain.append(round(redTimeGain, 2))
                                                        redTimeGain = 0
						elif owners[count2] == "None":
							redTimeGain += times[timeIndex]
						timeIndex+=1
					elif owners[count2-1] == "None":
                                                redTimeGain += times[timeIndex]
                                                if owners[count2] == "Blue":
							blueGain.append(times[timeIndex])
							blueGainedAt.append(rowTimes[count2])
						elif owners[count2] == "Red":
							redGain.append(round(redTimeGain, 2))
							redGainedAt.append(rowTimes[count2])
							redTimeGain = 0
							redHasOwn = True
							blueHasOwn = False
                                count2+=1
                        if owners[len(owners)-1] == "Red" and redHasOwn == True:
                                redOwn.append(rowTimes[len(owners)-1])
                        elif owners[len(owners)-1] == "Blue" and blueHasOwn == True:
                                blueOwn.append(rowTimes[len(owners)-1])
                                
                else:
                        firstTime = startTime
			rowTimes.append(firstTime)
			for r in rows:
                                rowTimes.append(round(time_min_to_sec(r[oTime]), 2))
                        #print ("TIMES" + str(rowTimes))
                        count = 0
                        while count < len(rows):
                                times.append(round(rowTimes[count] - rowTimes[count+1], 2))
                                count+=1
                        #print ("TIMES SORTED" + str(times))
			
                        owners = []
                        owners.append(o)
                        for r in rows:
                                owners.append(r[oOwner])

                        #print("OWNER" + str(owners))
			
                        count2 = 1
                        timeIndex = 0
                        redTimeGain = 0
			blueTimeGain = 0
			redHasOwn = True if o == "Red" else False
			blueHasOwn = True if o == "Blue" else False
		
                        while count2 < len(owners):
                                if redHasOwn == False and blueHasOwn == False:
                                        if owners[count2] == "Red":
                                                redGain.append(times[timeIndex])
                                                redGainedAt.append(rowTimes[count2])
						redHasOwn = True
                                                if owners[count2-1] == "Blue":
                                                        blueOwn.append(times[timeIndex])
                                                        blueHasOwn = False
                                                elif owners[count2-1] == "None":
                                                        blueTimeGain += times[timeIndex]
                                        elif owners[count2] == "Blue":
                                                blueGain.append(times[timeIndex])
                                                blueGainedAt.append(rowTimes[count2])
                                                blueHasOwn = True
                                                if owners[count2-1] == "Red":
                                                        redOwn.append(times[timeIndex])
                                                        redHasOwn = False
                                                elif owners[count2-1] == "None":
                                                        redTimeGain += times[timeIndex]
                                        timeIndex+=1
				elif redHasOwn == True:
                                        if owners[count2-1] == "Red":
                                                if owners[count2] == "Blue":
                                                        redOwn.append(times[timeIndex])
                                                        redHasOwn = False
							blueHasOwn = True
                                                        blueTimeGain += times[timeIndex]
                                                        blueGainedAt.append(rowTimes[count2])
                                                        blueGain.append(round(blueTimeGain,2))
                                                        blueTimeGain = 0
						elif owners[count2] == "None":
							blueTimeGain += times[timeIndex]
						timeIndex+=1
					elif owners[count2-1] == "None":
                                                blueTimeGain += times[timeIndex]
                                                if owners[count2] == "Red":
							redGain.append(times[timeIndex])
							redGainedAt.append(rowTimes[count2])
						elif owners[count2] == "Blue":
                                                        blueGain.append(round(blueTimeGain, 2))
							blueGainedAt.append(rowTimes[count2])
							blueTimeGain = 0
							blueHasOwn = True
							redHasOwn = False			
				elif blueHasOwn == True:
                                        if owners[count2-1] == "Blue":
                                                if owners[count2] == "Red":
                                                        blueOwn.append(times[timeIndex])
							blueHasOwn = False
							redHasOwn = True
							redTimeGain += times[timeIndex]
                                                        redGainedAt.append(rowTimes[count2])
							redGain.append(round(redTimeGain, 2))
                                                        redTimeGain = 0
						elif owners[count2] == "None":
							redTimeGain += times[timeIndex]
						timeIndex+=1
					elif owners[count2-1] == "None":
                                                redTimeGain += times[timeIndex]
                                                if owners[count2] == "Blue":
							blueGain.append(times[timeIndex])
							blueGainedAt.append(rowTimes[count2])
						elif owners[count2] == "Red":
							redGain.append(round(redTimeGain, 2))
							redGainedAt.append(rowTimes[count2])
							redTimeGain = 0
							redHasOwn = True
							blueHasOwn = False
                                count2+=1
                        if owners[len(owners)-1] == "Red" and redHasOwn == True:
                                redOwn.append(rowTimes[len(owners)-1])
                        elif owners[len(owners)-1] == "Blue" and blueHasOwn == True:
                                blueOwn.append(rowTimes[len(owners)-1])
                        #print ("RED GAIN " + str(redGain))
                        #print ("RED GAINED AT " + str(redGainedAt))
                        #print ("RED OWN " + str(redOwn))
                        #print ("BLUE GAIN " + str(blueGain))
                        #print ("BLUE GAINED AT " + str(blueGainedAt))
                        #print ("BLUE OWN " + str(blueOwn))
	allTimes = [redGain, redGainedAt, redOwn, blueGain, blueGainedAt, blueOwn]
	return allTimes	
		
def time_min_to_sec(time):
	mins = float(time[:time.find(':')])
	sec = float(time[time.find(':')+1:])
	sec += mins*60
	return sec
