# ============================================
# HL7 Test Message Sender
# Sends a test ADT^A01 (Patient Admission) message to Mirth Connect
# ============================================

# MLLP Framing Characters
# HL7 messages sent over TCP must be wrapped with special characters
# This is called MLLP (Minimum Lower Layer Protocol)
$VT = [char]0x0B    # Vertical Tab (0x0B) = START of HL7 message marker
$FS = [char]0x1C    # File Separator (0x1C) = END of HL7 message marker  
$CR = [char]0x0D    # Carriage Return (0x0D) = line ending in HL7

# ============================================
# BUILD THE HL7 MESSAGE
# HL7 messages have SEGMENTS separated by carriage returns
# Each segment starts with a 3-letter code (MSH, PID, etc.)
# Fields inside segments are separated by pipe | character
# ============================================

# MSH = Message Header Segment (always first in every HL7 message)
# Contains: sending app, receiving app, timestamp, message type, version
# ADT^A01 = Admit/Discharge/Transfer message, event A01 = Patient Admitted
$hl7 = "MSH|^~\&|HOSPITAL|FACILITY|MIRTH|CONNECT|20240418120000||ADT^A01|MSG001|P|2.3" + $CR

# PID = Patient Identification Segment
# Contains: patient ID, name, date of birth, gender, address, phone
# Nguyen^Viet^T = Last^First^Middle (HL7 name format)
# 19880914 = date of birth (YYYYMMDD)
# M = Male
$hl7 += "PID|1||PAT001^^^HOSPITAL^MR||Nguyen^Viet^T||19880914|M|||123 Main St^^KL^^50000^MY||+60123456789" + $CR

# WRAP the message with MLLP framing
# Final message structure: [VT] + HL7 content + [FS] + [CR]
$message = $VT + $hl7 + $FS + $CR

# ============================================
# SEND THE MESSAGE VIA TCP TO MIRTH CONNECT
# Mirth channel is listening on port 6661
# ============================================

# Open a TCP connection to Mirth (localhost = your own computer, port 6661)
$client = New-Object System.Net.Sockets.TcpClient("localhost", 6661)

# Get the network stream (like a pipe to send data through)
$stream = $client.GetStream()

# Convert the HL7 message string to bytes (computers send bytes, not strings)
$bytes = [System.Text.Encoding]::UTF8.GetBytes($message)

# Write (send) the bytes through the TCP connection to Mirth
$stream.Write($bytes, 0, $bytes.Length)

# Flush = make sure all bytes are actually sent (not stuck in a buffer)
$stream.Flush()

# Wait 2 seconds for Mirth to receive and process the message
Start-Sleep -Seconds 2

# Close the TCP connection — we're done sending
$client.Close()

# Print confirmation message in PowerShell
Write-Host "HL7 message sent! Check C:\MirthMessages for the saved file."

# ============================================
# AFTER RUNNING THIS SCRIPT:
# 1. Check C:\MirthMessages folder for a new .hl7 file
# 2. Open Mirth Administrator -> Dashboard
# 3. Your channel should show Received: 1, Sent: 1
# ============================================