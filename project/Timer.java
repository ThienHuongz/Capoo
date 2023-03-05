package project;

public class Timer{
	
	Timer timer;
	int second;
	
	public void Timer()
	{
		
	}
	
	// To count timer
	public void simpleTimer()
	{
		timer = new Timer(1000, new ActionListener()) {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				second++;
			}
		});
	}
}
