package chipyard
import subsystem.rme._
import org.chipsalliance.cde.config.Config
import gemmini.{GemminiCustomConfig, GemminiCustomConfigs}

class CustomGemminiSoCConfig extends Config(
  

  new gemmini.GemminiCustomConfig ++



  // Set your custom L2 configs
  new chipyard.config.WithL2TLBs(512) ++

  new freechips.rocketchip.subsystem.WithInclusiveCache(
    nWays = 8,
    capacityKB = 1024,
//    outerLatencyCycles = 40,
  //  subBankingFactor = 4
  ) ++

  // Set the number of CPUs you want to create
  new chipyard.CustomGemmminiCPUConfigs.CustomCPU(1) ++
  new freechips.rocketchip.subsystem.WithExtMemSize((1 << 30) * 4L) ++
  new freechips.rocketchip.subsystem.WithNonblockingL1(6) ++

  
  new barf.WithTLDCachePrefetcher(new barf.MultiNextLinePrefetcherParams(handleVA = true)) ++
  new chipyard.config.WithTilePrefetchers ++

  // RME (assuming this is your custom fragment)
  new WithRME ++

  new chipyard.config.WithSystemBusWidth(GemminiCustomConfigs.customConfig.dma_buswidth) ++
  new chipyard.config.AbstractConfig
)
