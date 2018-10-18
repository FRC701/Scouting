import sqlite3
from team import *

conn = sqlite3.connect('C:\Workspace_2018\pyTK 2018\scouting.db')
c = conn.cursor()

def get_teams():
    global c
    c.execute('SELECT TeamNumber FROM TeamsAll')
    teams = c.fetchall()
    #save all teams as new team objects for data to be added later
    for t in teams:
        Team(t[0])
        print ("Added Team #: " + str(t[0]))
    return teams


def populate_teaminfo():
    global c
    for t in get_teams():
        c.execute('SELECT * FROM StatsAll WHERE TeamNumber = ' + str(t[0]))
        rowStats = c.fetchone()
        rowsForTeamStats = []
        
        while rowStats is not None:
            rowsForTeamStats.append(rowStats)
            rowStats = c.fetchone()
            
        store_stats(rowsForTeamStats)        
        
def store_stats(rows):
    #index
    matchNum=1
    teamNum=2
    matchPosition = 3
    noShow = 4
    hadAuto = 5
    crossedAuto = 6
    autoPickedUpCube = 7

    autoCubesInSw1 = 8
    autoCubesInScl = 9
    autoCubesInSw2 = 10
    autoCubesInEx = 11

    teleCubesInSw1 = 12
    teleCubesInScl = 14
    teleCubesInSw2 = 13
    teleCubesInEx = 15
    
    pickedUpCube = 16
    climb = 17
    climbAssist = 18
    parking = 19
    humanPlayer = 20
    disabled = 21
    redCard = 22
    yellowCard = 23
    foul = 24
    techFoul = 25
    
    for row in rows:
        for team in Team.team_list:
            if int(row[teamNum]) == team.number:
                team.Info.matches.append(row[matchNum])
                team.Info.noShow += row[noShow]
                team.Info.hadAuto += row[hadAuto]
                team.Info.crossAutoLine += row[crossedAuto]
                team.Info.autoPickedUpCube += row[autoPickedUpCube]

                team.Scores.autoCubesInSw1.append(row[autoCubesInSw1])
		team.Scores.autoCubesInScl.append(row[autoCubesInScl])
		team.Scores.autoCubesInSw2.append(row[autoCubesInSw2])
		team.Scores.autoCubesInEx.append(row[autoCubesInEx])
				
		team.Scores.teleCubesInSw1.append(row[teleCubesInSw1])
		team.Scores.teleCubesInScl.append(row[teleCubesInScl])
		print (str(team.number) + " Scl: " + str(row[teleCubesInScl]))
		team.Scores.teleCubesInSw2.append(row[teleCubesInSw2])
		team.Scores.teleCubesInEx.append(row[teleCubesInEx])
		                
		team.Info.telePickedUpCube += row[pickedUpCube]
		team.Info.climb += row[climb]
		team.Info.climbAssist += row[climbAssist]
		team.Info.parking += row[parking]
		team.Info.humanPlayer += row[humanPlayer]
		team.Info.disabled += row[disabled]
		team.Info.redCard += row[redCard]
		team.Info.yellowCard += row[yellowCard]
		team.Info.fouls.append(row[foul])
		team.Info.techFouls.append(row[techFoul])
				
		if row[foul] > 1 or row[techFoul] > 1:
		    team.Info.hasFoul += 1

		print ("added stats values for " + str(team.number))
		print (row)
		    
    
